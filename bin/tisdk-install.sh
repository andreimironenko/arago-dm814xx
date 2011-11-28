#! /usr/bin/env bash
#
#  (C) Copyright 2010 Texas Instrument Inc.  All rights reserved.
#
#  Author: Brijesh Singh, Texas Instrunents Inc.
#
#  Script to install TI SDK
#

VERSION="1.4"

#
# Display program usage
#
usage()
{
  echo "
Usage: `basename $1` [options] --machine <machine_name> --toolchain <toolchain path> <install_dir>

  --help                Print this help message
  --graphics            Install graphics packages
  --bsp                 Install board support packages
  --addons              Install addon demos/utilities
  --crypto              Install crypto applications/libraries
  --dsp                 Install c64p dsp packages
  --multimedia          Install multimedia packages
  --force               Force installation on unsupported host
  --toolchain           Where is toolchain installed
"
  exit 1
}

#
# Display version 
#
version()
{
  echo "
`basename $1`: version $VERSION 
"
  exit 1
}

#
# verify if cdrom has valid arch.conf and sdktools ipk
#
verify_cdrom ()
{
  echo "Checking $machine config"
  if [ ! -f config/$machine/opkg.conf ]; then
    echo "ERROR: opkg.conf does not exist for $machine"
    exit 1;
  fi
  echo "Updating opkg.conf "
  sed  "s|file:\(..*\)ipk|file:$PWD/deploy/ipk|g" config/$machine/opkg.conf > ${install_dir}/.opkg.conf
  sed  "s|file:\(..*\)ipk|file:$PWD/deploy/ipk|g" config/$machine/opkg-sdk.conf > ${install_dir}/.opkg-sdk.conf
  opkg_conf="${install_dir}/.opkg.conf"
  opkg_sdk_conf="${install_dir}/.opkg-sdk.conf"
}

#
# execute command
#
execute ()
{
  #echo "$*"
  $*
  if [ $? -ne 0 ]; then
    echo "ERROR: failed to execute $*"
    exit 1;
  fi
}

#
# update sdk Rules.make
#
update_rules_make()
{
  echo "Updating Rules.make"

  if [ -e ${install_dir}/usr/lib/opkg/info/*-sourcetree*.control ]; then
    control_files="${install_dir}/usr/lib/opkg/info/*-src*.control ${install_dir}/usr/lib/opkg/info/*-sourcetree*.control"
  else
    control_files="${install_dir}/usr/lib/opkg/info/*-src*.control"
  fi
  for i in ${control_files}; do
    name="`cat $i | grep Package | awk {'print $2'}`"
    
    echo $name | grep ti-*  >/dev/null
    # if package contain ti- prefix then remove it
    if [ $? -eq 0 ]; then
      name="`echo $name | cut -f2-5 -d-`"
    fi

    # remove -src from the end
    name="`echo $name | sed -e 's/-src$//g' -e 's/-sourcetree$//g'`"

    # Get the real name of the package
    case $name in
      "libgles-omap3")
        real_name="graphics-sdk"
        ;;
      "signal-analyzer-demo")
        real_name="signal-analyzer"
        ;;
      "omx-libs")
        real_name="omx"
        ;;
      "omtb")
        real_name="omtb"
        ;;
      "gst-openmax-ti")
        real_name="gst-openmax"
        ;;
      "sysbios")
        real_name="bios"
        ;;
      "codec-engine")
        real_name="codec_engine"
        ;;
      "framework-components")
        real_name="framework_components"
        ;;
      "imglib-c64plus")
        real_name="c64plus-imglib"
        ;;
      "dsplib-c674x")
        real_name="c674x-dsplib"
        ;;
      "mathlib-c67x")
        real_name="c67xmathlib"
        ;;
      *)
        real_name=$name
        ;;
    esac

    # get the directory name
    dirname="`find $install_dir -maxdepth 2 -name ${real_name}_* -type d | xargs basename`"

    # update rules.make
    sed -i -e s/\<__${name}__\>/${dirname}/g \
        $install_dir/usr/share/ti/Rules.make
  done
  
  # update LINUX KERNEL INSTALL DIR
  linuxversion="`basename $install_dir/board-support/linux-*`"
  sed -i -e s/\<__kernel__\>/${linuxversion}/g \
          $install_dir/usr/share/ti/Rules.make
  sed -i -e s=\<__SDK__INSTALL_DIR__\>=${install_dir}= \
    $install_dir/usr/share/ti/Rules.make
  sed -i -e s=\<__CROSS_COMPILER_PATH__\>=${TOOLCHAIN_PATH}= \
    $install_dir/usr/share/ti/Rules.make
  sed -i -e s=linuxlibs=linux-devkit/arm-none-linux-gnueabi/usr= \
    $install_dir/usr/share/ti/Rules.make
}

#
# move sourcetree in top label directory to give dvsdk finishing
#
move_to_install_dir()
{
  echo "Moving sourcetree"
  if [ -d ${install_dir}/usr/share/ti/ti-psp-tree ]; then
    echo " from ti-psp-tree => board-support"
    mv ${install_dir}/usr/share/ti/ti-psp-tree/* ${install_dir}/board-support/

    rm -rf ${install_dir}/usr/share/ti/ti-psp-tree
  fi

  # copy prebuilt kernel image and uboot in board-support/prebuilt directory
  mkdir -p ${install_dir}/board-support/prebuilt-images/
  cp deploy/images/$machine/*.bin ${install_dir}/board-support/prebuilt-images/
 
  # copy prebuilt MLO to board-support/prebuilt directory
  if [ -e deploy/images/$machine/MLO ]; then
    cp deploy/images/$machine/MLO ${install_dir}/board-support/prebuilt-images/
  fi

  # copy prebuilt kernel modules to board-support/prebuilt directory
  cp deploy/images/$machine/modules*$machine.tgz ${install_dir}/board-support/prebuilt-images/

  if [ -d ${install_dir}/usr/share/ti/ti-docs-tree ]; then
    echo " from ti-docs-tree => docs"
    mv ${install_dir}/usr/share/ti/ti-docs-tree/* ${install_dir}/docs/
    rmdir ${install_dir}/usr/share/ti/ti-docs-tree
  fi

  mv ${install_dir}/usr/share/ti/* ${install_dir}/
  rm -rf $install_dir/usr/share/ti
  rm -rf $install_dir/usr/lib/opkg
  rm -rf $install_dir/usr/libexec
  rm -rf $install_dir/lib
#   rm -rf $install_dir/etc
  rm -rf $install_dir/sbin
}

# the Source entry other than the repository where the sources were
# downloaded from.
print_arago_entries() {
    echo "<br>Files from:<br><a href=git://arago-project.org/git/arago.git>git://arago-project.org/git/arago.git</a><br><a href=git://arago-project.org/git/arago-oe-dev.git>git://arago-project.org/git/arago-oe-dev.git</a>"
}


#
# Generate software manifest file
#
generate_sw_manifest()
{
if [ ! -d $2/usr/lib/opkg/info  ]; then
  return;
fi

echo "
<h2><u>$1</u></h2>
<table border=1 cellspacing=1 cellpadding=1 width=80%>
<tr bgcolor=#c0c0c0  color=white><td><b>Package Name</b></td><td><b>Version</b></td><td><b>License</b></td><td><b>Location</b></td><td><b>Delivered As</b></td> <td><b>Modified</b></td><td><b>Obtained from</b></td></tr>
"

  for i in $2/usr/lib/opkg/info/*.control; do
    package="`cat $i | grep Package: | awk {'print $2'}`"
    version="`cat $i | grep Version: | awk {'print $2'} | cut -f1-2 -d-`"
    license="`cat $i | grep License: | awk {'print $2,$3,$4'} `"
    sources="`cat $i | grep Source: | cut -d ':' -f2-`"
    long_version="`cat $i | grep Version: | awk {'print $2'}`"
    architecture="`cat $i | grep Architecture: | awk {'print $2'}`"
    location="$package""_""$long_version""_""$architecture"".ipk"

    case "$license" in 
      *unknown*) highlight="bgcolor=yellow" ;;
      *GPLv3*) highlight="bgcolor=yellow" ;;
      *) highlight="" ;;
    esac

    # source variable contains the text to be used in the manifest
    source=""
    # Are there additional files in the package that come from the
    # arago/OE metadata?
    extra_files="0"
    modified="No"
    for s in $sources
    do
        case "$s" in
          file://*)
            extra_files="1"
            source="http://www.ti.com"
            ;;
          http://install.source.dir.local*)
            # If we are pulling something from a local file system then
            # it is not a public modification and this should be marked
            # as modified.  This should not be the normal case.
            source="<a href='http://www.ti.com'>TI</a>"
            modified="Yes"
            ;;
          *india.ti.com*) 
            source="<a href='http://www.ti.com'>TI</a>"
            modified="Yes"
            ;;
          *sanb.design.ti.com*)
            source="<a href='http://www.ti.com'>TI</a>"
            modified="Yes"
            ;;
          *dal.design.ti.com*)
            source="<a href='http://www.ti.com'>TI</a>"
            modified="Yes"
            ;;
          *)
            source="$source""<a href=$s>$s</a>";;
        esac
    done

    if [ "$extra_files" == "1" ]
    then
        source="$source"`print_arago_entries`
    fi

    package_name=$package
    case "$package" in
      task-*) continue ;;
      libgles-omap3-src)
            package_name="$package (SGX GLES1.1 drivers and GLES2.0 drivers)"
            delivered_as="Binary"
            modified="Yes"
            license="TI Proprietary"            
            source="<a href='http://www.imgtec.com'>Imagination Technologies</a>"
            ;;
      ti-cgt6x-src)
            delivered_as="Binary"
            modified="No"
            license="TI Proprietary"            
            source="<a href='http://www.ti.com'>TI</a>"
            ;;
      ti-cgt470-src)
            delivered_as="Binary"
            modified="No"
            license="TI Proprietary"            
            source="<a href='http://www.ti.com'>TI</a>"
            ;;
      *-src) delivered_as="Source and Binary"
            modified="Yes" 
            location="$2"
            ;; 
      ti-*) delivered_as="Source and Binary"
            location="$2"
            modified="Yes" ;; 
      *blitrix*) 
            package_name="$package (Computer software for performing 2D graphics operations)"
            license="Draw Elements"
            source="http://www.drawelements.com" ;; 
      *libticpublt*) 
            package_name="$package (Computer software for performing 2D graphics operations)"
            license="Draw Elements"
            source="http://www.drawelements.com" ;; 
      *)    delivered_as="Binary"
            location="$2"
            modified="No";;
            
    esac

    # Check if this package has a directory in the Arago SDK licenses
    # directory.  If so copy it to the top-level "docs" licenses directory.
    if [ -e ${install_dir}/linux-devkit/licenses/$package ]
    then
        if [ ! -e ${install_dir}/docs/licenses ]
        then
            mkdir -p ${install_dir}/docs/licenses
        fi
        mv ${install_dir}/linux-devkit/licenses/$package ${install_dir}/docs/licenses/ > /dev/null 2>&1
    fi
    
    echo "
<tr><td>${package_name} </td><td>${version}</td> <td $highlight> ${license} </td><td>$location</td><td>$delivered_as</td><td>$modified</td> <td>$source</td></tr>
"
  done
echo "</table><br><br>"
}

#
# software manifest header
# 
sw_manifest_header()
{
echo "
<HTML>
<HEAD>
<TITLE>
$machine EZ SDK ${SDK_VERSION} Installation Summary 
</TITLE>
</HEAD>
<BODY>
<h1><CENTER> $machine EZ SDK ${SDK_VERSION} Software Manifest </CENTER></h1>
<h2><b><u>Legend</u></b></h2>
<table border=1 width=45%>
<tr><td>Package Name</td><td>The name of the application or files</td></tr>
<tr><td>Version</td><td>Version of the application or files</td></tr>
<tr><td>License</td><td>Name of the license or licenses that apply to the Package.</td></tr>
<tr><td>Location</td><td>The directory name and path on the media (or in an archive) where the Package is located.</td></tr>
<tr><td>Delivered As</td><td>This field will either be &ldquo;Source&rdquo;, &ldquo;Binary&rdquo; or &ldquo;Source and Binary&ldquo; and is the form the content of the Package is delivered in.  If the Package is delivered in an archive format, this field applies to the contents of the archive. If the word Limited is used with Source, as in &ldquo;Limited Source&rdquo; or &ldquo;Limited Source and Binary&rdquo; then only portions of the Source for the application are provided.</td></tr>
<tr><td>Modified </td><td>This field will either be &ldquo;Yes&rdquo; or &ldquo;No&rdquo;. A &ldquo;Yes&rdquo; means TI had made changes to the Package. A &ldquo;No&rdquo; means TI has not made any changes.</td></tr>
<tr><td>Obtained from</td><td>This field specifies where TI obtained the Package from. It may be a URL to an Open Source site, a 3rd party company name or TI. If this field contains a link to an Open Source package, the date it was downloaded is also recorded.</td></tr>
</table>

<p>&nbsp;</p>
<p><b>
<span style='font-size: 12.0pt; text-decoration: underline'>DISCLAIMERS</span></b></p>
<p><b>Export Control Classification Number (ECCN)</b></p>
<p style='margin-left: .5in'>Any use of ECCNs listed in the 
Manifest is at the user.s risk and without recourse to TI. &nbsp;&nbsp;Your company, as 
the exporter of record, is responsible for determining the correct 
classification of any item at the time of export. Any export classification by 
TI of Software is for TI.s internal use only and shall not be construed as a 
representation or warranty regarding the proper export classification for such 
Software or whether an export license or other documentation is required for 
exporting such Software<b><span style='font-size:12.0pt'>.&nbsp; </span></b></p>
<p><b>Links in the Manifest</b></p>
<p style='margin-left:.5in'>Any links appearing on this Manifest 
(for example in the .Obtained from. field) were verified at the time the 
Manifest was created. TI makes no guarantee that any listed links will remain 
active in the future.</p>
<p><b>Open Source License References</b></p>
<p style='margin-left:.5in'>Your company is responsible for 
confirming the applicable license terms for any open source Software listed in 
this Manifest that was not .Obtained from. TI.&nbsp; Any open source license 
specified in this Manifest for Software that was not .Obtained from. TI is for 
TI.s internal use only and shall not be construed as a representation or 
warranty regarding the proper open source license terms for such Software.</p>
<b><span style='font-size: 12.0pt; font-family: Arial'>
<br clear='all' style='page-break-before: always'></span></b><p>&nbsp;</p>
"
}

#
# sw manifest footer
#
sw_manifest_footer()
{
echo "
<hr>
<right> <i><font color=grey>Auto generated by TI EZ SDK installer on `date`</i> </right></font>
</body>
</html>
"
}

#
# Check if package should be printed in the BOM
#
display_in_bom()
{
    # echo "Checking for $1"
    case "$1" in
      *ti-*) return 1 ;;
      *qt4-embedded*) return 1 ;;
      *linux-omap3*) return 1 ;;
      *u-boot*) return 1 ;;
      *) return 0 ;;
    esac
}

#
# Generate software BOM file
#
generate_sw_bom()
{
if [ ! -d $1/usr/lib/opkg/info  ]; then
  return;
fi

  for i in $1/usr/lib/opkg/info/*.control; do
    package="`cat $i | grep Package: | awk {'print $2'}`"
    version="`cat $i | grep Version: | awk {'print $2'} | cut -f1-2 -d-`"

    display_in_bom ${package}
    if [ $? -eq "1" ]; then
       echo "
<tr><td>${package} </td><td>${version}</td></tr>
"
    fi
  done
}

#
# software BOM header
# 
sw_bom_header()
{
echo "
<HTML>
<HEAD>
<TITLE>
$machine EZ SDK ${SDK_VERSION} Installation Build of Materials
</TITLE>
</HEAD>
<BODY>
<h1><CENTER> $machine EZ SDK ${SDK_VERSION} Build of Materials </CENTER></h1>
<h2><b><u>Legend</u></b></h2>
<table border=1 width=45%>
<tr><td>Package Name</td><td>The name of the application or files</td></tr>
<tr><td>Version</td><td>Version of the application or files</td></tr>
<p>&nbsp;</p>
<table border=1 cellspacing=1 cellpadding=1 width=80%>
<tr bgcolor=#c0c0c0  color=white><td><b>Package Name</b></td><td><b>Version</b></td></tr>
"
}

#
# sw bom footer
#
sw_bom_footer()
{
echo "
</table><br><br>
<hr>
<right> <i><font color=grey>Auto generated by TI EZ SDK installer on `date`</i> </right></font>
</body>
</html>
"
}

#
# prepare installer tools - this requires building opkg and fakeroot natively
# 
prepare_install ()
{
  cwd=$PWD;
  if [ ! -f install-tools.tgz ]; then
    echo "ERROR: failed to find install-tools.tgz"
    exit 1;
  fi

  execute "mkdir -p ${install_dir}"
  execute "tar zxf install-tools.tgz -C ${install_dir}"
  if [ ! -d ${install_dir}/install-tools/ ]; then
    echo "ERROR: failed to find installation tool"
    exit 1;
  fi

  export PATH=${install_dir}/install-tools/bin:$PATH
  export LD_LIBRARY_PATH=${install_dir}/install-tools/lib:$LD_LIBRARY_PATH
  
  # verify if fakeroot command is present on host.
  which fakeroot
  if [ $? -ne 0 ]; then
    # if force_host is passed then build the command natively
    if [ "$force_host" = "yes" ]; then
      cwd=$PWD;
      execute "cd ${install_dir}/install-tools/"
      execute "tar zxf fakeroot*.tar.gz"
      execute "cd fakeroot*"
      execute "./configure --prefix=${install_dir}/install-tools"
      execute "make"
      execute "make install"
      export LD_LIBRARY_PATH=${install_dir}/install-tools/lib:$LD_LIBRARY_PATH
      cd $cwd
    else
      echo "ERROR: failed to find fakeroot"
      exit 1
    fi
  fi

  # if force_host option is passed then natively build opkg-cl command.
  if [ "$force_host" = "yes" ]; then
    cwd=$PWD;
    execute "cd ${install_dir}/install-tools/"
    execute "tar zxf opkg-src*.tar.gz"
    execute "cd opkg*"
    execute "./autogen.sh --disable-curl --disable-gpg --prefix=${install_dir}/install-tools"
    execute "make"
    execute "make install"
    export LD_LIBRARY_PATH=${install_dir}/install-tools/lib:$LD_LIBRARY_PATH
    cd $cwd
  fi
}

#
# install sourcetree or devel packages on host
#
host_install ()
{
  mkdir -p ${install_dir}/usr/lib/opkg
  echo "Install $bsp_src $addons_src $crypto_src $dsp_src $multimedia_src $graphics_src ti-tisdk-makefile"
  execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir -f ${opkg_conf}  update"
  execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir -f ${opkg_conf} install  $bsp_src $addons_src $dsp_src $multimedia_src $graphics_src ti-tisdk-makefile"
  execute "opkg-cl  --cache ${install_dir}/deploy/cache -o ${install_dir} -f ${opkg_conf} remove  -force-depends  libc6 update-rc.d"

  update_rules_make

  generate_sw_manifest "Packages installed on the host machine:" "$install_dir" >> ${install_dir}/docs/software_manifest.htm;
  generate_sw_bom "$install_dir" >> ${install_dir}/docs/software_bom.htm;

  move_to_install_dir
}

install_graphics_sdk_host ()
{
  script="$install_dir/linux-devkit/environment-setup"
  execute "mkdir -p $install_dir/usr"
  execute "cp -ar $install_dir/linux-devkit/usr/lib $install_dir/usr"
  execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir -f ${opkg_sdk_conf}  update"
  ! test -z $graphics && execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir -f ${opkg_sdk_conf}  install --nodeps $graphics_sdk_host "
  execute "cp -ar $install_dir/usr/lib $install_dir/linux-devkit/usr/"
  execute "rm -rf $install_dir/usr"
  echo 'export OE_QMAKE_CC=${TARGET_SYS}-gcc' >> $script
  echo 'export OE_QMAKE_CXX=${TARGET_SYS}-g++' >> $script
  echo 'export OE_QMAKE_LINK=${TARGET_SYS}-g++' >> $script
  echo 'export OE_QMAKE_AR=${TARGET_SYS}-ar' >> $script
  echo 'export OE_QMAKE_LIBDIR_QT=${SDK_PATH}/${TARGET_SYS}/usr/lib' >> $script
  echo 'export OE_QMAKE_INCDIR_QT=${SDK_PATH}/${TARGET_SYS}/usr/include/qtopia' >> $script
  echo 'export OE_QMAKE_MOC=${SDK_PATH}/bin/moc4' >> $script
  echo 'export OE_QMAKE_UIC=${SDK_PATH}/bin/uic4' >> $script
  echo 'export OE_QMAKE_UIC3=${SDK_PATH}/bin/uic34' >> $script
  echo 'export OE_QMAKE_RCC=${SDK_PATH}/bin/rcc4' >> $script
  echo 'export OE_QMAKE_QDBUSCPP2XML=${SDK_PATH}/bin/qdbuscpp2xml4' >> $script
  echo 'export OE_QMAKE_QDBUSXML2CPP=${SDK_PATH}/bin/qdbusxml2cpp4' >> $script
  echo 'export OE_QMAKE_QT_CONFIG=${SDK_PATH}/${TARGET_SYS}/usr/share/qtopia/mkspecs/qconfig.pri' >> $script
  echo 'export QMAKESPEC=${SDK_PATH}/${TARGET_SYS}/usr/share/qtopia/mkspecs/linux-g++' >> $script
  echo 'export OE_QMAKE_LDFLAGS="-L${SDK_PATH}/${TARGET_SYS}/usr/lib -Wl,-rpath-link,${SDK_PATH}/${TARGET_SYS}/usr/lib -Wl,-O1 -Wl,--hash-style=gnu"' >> $script
  echo 'export OE_QMAKE_STRIP="echo"' >> $script

}

#
# install arago sdk
#
install_arago_sdk ()
{
  if [ ! -f devel/arago*.tar.bz2 ]; then
    echo "ERROR: failed to find arago sdk tarball"
    exit 1
  fi
  arago_sdk="`ls -1 devel/arago*.tar.bz2`"
  echo "Installing linux-devkit ($arago_sdk)"
  execute "tar jxf ${arago_sdk} -C ${install_dir}"

  execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf}  update"

  ! test -z $graphics && execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf}  install $graphics_sdk_target "
  ! test -z $multimedia && execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf}  install $multimedia_sdk_target "
  ! test -z $dsp && execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf}  install $dsp_sdk_target "
  ! test -z $crypto && execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf}  install $crypto_sdk_target "

  # remove these packages (see arago/meta/meta-toolchain-target.bb)
  execute "opkg-cl  --cache ${install_dir}/deploy/cache -o ${install_dir}/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf} remove  -force-depends libc6 libc6-dev glibc-extra-nss libgcc1 linux-libc-headers-dev libthread-db1 sln gettext gettext-dev libgettextlib libgettextsrc"
  execute "opkg-cl  --cache ${install_dir}/deploy/cache -o ${install_dir}/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf} remove  -force-depends gnome* python* gtk* pango* tcl* tk* cairo* openssl* avahi*"

  ! test -z $graphics && install_graphics_sdk_host 

  echo "Running demangle_libtool.sh to fix *.la files"
  execute "demangle_libtool.sh $install_dir/linux-devkit/arm-none-linux-gnueabi/usr/lib/*.la" 2> /dev/null
  
  echo "Updating SDK_PATH env"        
  sed -i "1{s|SDK_PATH\(..*\)|SDK_PATH=$install_dir/linux-devkit/|g}" $install_dir/linux-devkit/environment-setup
  sed -i "2{s|TOOLCHAIN_PATH\(..*\)|TOOLCHAIN_PATH=${TOOLCHAIN_PATH}|g}" $install_dir/linux-devkit/environment-setup
  echo "export PS1=\"\[\e[32;1m\][linux-devkit]\[\e[0m\]:\w> \"" >> $install_dir/linux-devkit/environment-setup

  echo "Updating prefix in libtoolize "
  sed -i -e "s|/linux-devkit|$install_dir/linux-devkit|g" \
    $install_dir/linux-devkit/bin/libtoolize 
}

# unset TOOLCHAIN_PATH exported by other script.
unset TOOLCHAIN_PATH

# Process command line...
while [ $# -gt 0 ]; do
  case $1 in
    --help | -h)
      usage $0
      ;;
    --version | -v)
      version $0
      ;;
    --graphics)
      graphics_bin="task-arago-tisdk-graphics";
      graphics_src="task-arago-toolchain-tisdk-graphics-host";
      graphics_sdk_target="task-arago-toolchain-tisdk-graphics-target";
      graphics_sdk_host="qt4-tools-sdk";
      graphics="yes";
      shift;
      ;;
    --bsp)
      bsp_src="task-arago-toolchain-tisdk-bsp-host";
      bsp_bin="task-arago-tisdk-bsp";
      bsp="yes";
      shift;
      ;;
    --crypto)
      crypto_src="task-arago-toolchain-tisdk-crypto-host";
      crypto_bin="task-arago-tisdk-crypto";
      crypto_sdk_target="task-arago-toolchain-tisdk-crypto-target";
      crypto="yes";
      shift;
      ;;
    --addons)
      addons_src="task-arago-toolchain-tisdk-addons-host";
      addons_bin="task-arago-tisdk-addons";
      addons="yes";
      shift;
      ;;
    --dsp)
      dsp_src="task-arago-toolchain-tisdk-dsp-host";
      dsp_bin="task-arago-tisdk-dsp";
      dsp_sdk_target="task-arago-toolchain-tisdk-dsp-target";
      dsp="yes";
      shift;
      ;;
    --multimedia)
      multimedia_src="task-arago-toolchain-tisdk-multimedia-host"
      multimedia_bin="task-arago-tisdk-multimedia"
      multimedia_sdk_target="task-arago-toolchain-tisdk-multimedia-target";
      multimedia="yes";
      shift;
      ;;
    --machine)
      shift
      machine=$1;
      shift;
      ;;
    --force)
      shift
      force_host="yes";
      ;;
    --toolchain)
      shift
      TOOLCHAIN_PATH=$1;
      shift;
      ;;
     *)
      install_dir=$1;
      shift;
      ;;
  esac
done

# check if machine is defined.
test -z $machine && usage $0

# HACK: Force the machine family.
# We need to fix the installer flow for this to be clean.
machine_family="C6A816x/AM389x" 

# check if toolchain path is defined.
test -z $TOOLCHAIN_PATH && usage $0

# check if install_dir variable is set.
test -z $install_dir && usage $0
execute "mkdir -p $install_dir"

# verify if cdrom has architecture specific opkg.conf.
verify_cdrom

# prepare installation.
prepare_install

# TODO: IJ installer does not pass --crypto flag yet
# Temporarly based on machine type we enable crypto
if [ "$machine" = "dm37x-evm" ]; then
    crypto_src="task-arago-toolchain-tisdk-crypto-host";
    crypto_bin="task-arago-tisdk-crypto";
    crypto_sdk_target="task-arago-toolchain-tisdk-crypto-target";
    crypto="yes";
fi 

if [ "$machine" = "da850-omapl138-evm" ]; then
    crypto_src="task-arago-toolchain-tisdk-crypto-host";
    crypto_bin="task-arago-tisdk-crypto";
    crypto_sdk_target="task-arago-toolchain-tisdk-crypto-target";
    crypto="yes";
fi 

# create software manifest header and bom header.
mkdir -p $install_dir/docs
sw_manifest_header > ${install_dir}/docs/software_manifest.htm
sw_bom_header > ${install_dir}/docs/software_bom.htm

# install arago sdk
# NOTE:  This should be installed before any other packages so that
#        licenses are available to by copied when making the 
#        software manifest.
install_arago_sdk

# install sourcetree (or devel) ipk on host.
host_install

# install binary ipk on target.
mkdir -p ${install_dir}/filesystem
cp deploy/images/$machine/*.tar.gz ${install_dir}/filesystem
export DVSDK_INSTALL_MACHINE="$machine"
echo "Install  $install_dir $bsp_bin $crypto_bin $addons_bin $multimedia_bin $graphics_bin $dsp_bin"
fakeroot install_rootfs.sh $install_dir $bsp_bin $addons_bin $crypto_bin $multimedia_bin $graphics_bin $dsp_bin 

tar zxf `ls -1 ${install_dir}/filesystem/*.tar.gz` -C $install_dir/filesystem --wildcards *.control*
generate_sw_manifest "Packages installed on the target:" "$install_dir/filesystem" >> ${install_dir}/docs/software_manifest.htm
rm -rf ${install_dir}/filesystem/usr

# copy original image on filesystem directory.
cp deploy/images/$machine/*.tar.gz ${install_dir}/filesystem

generate_sw_manifest "Packages installed on arago-sdk host side:" "$install_dir/linux-devkit" >> ${install_dir}/docs/software_manifest.htm
generate_sw_bom "$install_dir/linux-devkit" >> ${install_dir}/docs/software_bom.htm;

generate_sw_manifest "Packages installed on arago-sdk target side:" "$install_dir/linux-devkit/arm-none-linux-gnueabi" >> ${install_dir}/docs/software_manifest.htm

# add manifest and BOM footer.
sw_manifest_footer >> ${install_dir}/docs/software_manifest.htm
sw_bom_footer >> ${install_dir}/docs/software_bom.htm


mkdir -p ${install_dir}/etc
cp ${opkg_conf} ${install_dir}/etc/opkg.conf
rm -rf ${opkg_conf}
rm -rf ${opkg_sdk_conf}
rm -rf ${install_dir}/install-tools
rm -rf $install_dir/var

# TODO: don't know which package is creating include directory on top level.
# for now remove the directory.
rm -rf ${install_dir}/include

# Remove licenses directory from Arago SDK since any applicable licenses
# have already been copied to the top-level docs/licenses directory
rm -rf ${install_dir}/linux-devkit/licenses

echo "Installation completed!"

exit 0
 
