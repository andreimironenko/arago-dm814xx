#! /usr/bin/env bash
#
#  (C) Copyright 2010 Texas Instrument Inc.  All rights reserved.
#
#  Author: Brijesh Singh, Texas Instrunents Inc.
#
#  Script to install TI SDK
#

VERSION="1.3"

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
  for i in ${install_dir}/usr/lib/opkg/info/*-src*.control; do
    name="`cat $i | grep Package | awk {'print $2'}`"
    
    echo $name | grep ti-*  >/dev/null
    # if package contain ti- prefix then remove it
    if [ $? -eq 0 ]; then
      name="`echo $name | cut -f2-5 -d-`"
    fi

    # remove -src from the end
    name="`echo $name | sed -e 's/....$//g'`"

    # get the directory name
    dirname="`basename $install_dir/${name}_*`"

    if [ "$name" = "libgles-omap3" ]; then
       dirname="`basename $install_dir/omap35x_graphics_sdk_*`"
    fi

    # update rules.make
    sed -i -e s/\<__${name}__\>/${dirname}/g \
        $install_dir/usr/share/ti/Rules.make
  done
  
  # update LINUX KERNEL INSTALL DIR
  linuxversion="`basename $install_dir/psp/linux-*`"
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
    echo " from ti-psp-tree => psp"
    mv ${install_dir}/usr/share/ti/ti-psp-tree/* ${install_dir}/psp/

    # copy prebuilt kernel image and uboot in psp/prebuilt directory
    mkdir -p ${install_dir}/psp/prebuilt-images/
    cp deploy/images/$machine/*.bin ${install_dir}/psp/prebuilt-images/

    rm -rf ${install_dir}/usr/share/ti/ti-psp-tree
  fi

  if [ -d ${install_dir}/usr/share/ti/ti-docs-tree ]; then
    echo " from ti-docs-tree => docs"
    mv ${install_dir}/usr/share/ti/ti-docs-tree/* ${install_dir}/docs/
    rmdir ${install_dir}/usr/share/ti/ti-docs-tree
  fi

  mv ${install_dir}/usr/share/ti/* ${install_dir}/
  rm -rf $install_dir/usr
  rm -rf $install_dir/lib
  rm -rf $install_dir/etc
  rm -rf $install_dir/sbin
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
    source="`cat $i | grep Source: | awk {'print $2'} | cut -f1 -d\;`"
    case "$license" in 
      *unknown*) highlight="bgcolor=yellow" ;;
      *GPLv3*) highlight="bgcolor=yellow" ;;
      *) highlight="" ;;
    esac

    case "$source" in
      file://*) source="";;
      http://install.source.dir.local*) source="";;
      *) ;;
    esac

    case "$package" in
      task-*) continue ;;
      *-src) delivered_as="Source and Binary"
            modified="Yes" 
            location="$2"
            ;; 
      ti-*) delivered_as="Source and Binary"
            location="$2"
            modified="Yes" ;; 
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
<tr><td>${package} </td><td>${version}</td> <td $highlight> ${license} </td><td>$location</td><td>$delivered_as</td><td>$modified</td> <td><a href=$source>$source</a></td></tr>
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
$machine DVSDK ${DVSDK_VERSION} Installation Summary 
</TITLE>
</HEAD>
<BODY>
<h1><CENTER> $machine DVSDK ${DVSDK_VERSION} Software Manifest </CENTER></h1>
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
"
}

#
# sw manifest footer
#
sw_manifest_footer()
{
echo "
<hr>
<right> <i><font color=grey>Auto generated by TI DVSDK installer on `date`</i> </right></font>
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
  echo "Install $bsp_src $addons_src $dsp_src $multimedia_src $graphics_src ti-tisdk-makefile"
  execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir -f ${opkg_conf}  update"
  execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir -f ${opkg_conf} install  $bsp_src $addons_src $dsp_src $multimedia_src $graphics_src ti-tisdk-makefile"

  update_rules_make

  generate_sw_manifest "Packages installed on the host machine:" "$install_dir" >> ${install_dir}/docs/software_manifest.htm;

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
  if [ ! -f devel/arago*.tar.gz ]; then
    echo "ERROR: failed to find arago sdk tarball"
    exit 1
  fi
  arago_sdk="`ls -1 devel/arago*.tar.gz`"
  echo "Installing linux-devkit ($arago_sdk)"
  execute "tar zxf ${arago_sdk} -C ${install_dir}"

  execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf}  update"

  ! test -z $graphics && execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf}  install $graphics_sdk_target "
  ! test -z $multimedia && execute "opkg-cl --cache $install_dir/deploy/cache -o $install_dir/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf}  install $multimedia_sdk_target "

  # remove these packages (see arago/meta/meta-toolchain-target.bb)
  execute "opkg-cl  --cache ${install_dir}/deploy/cache -o ${install_dir}/linux-devkit/arm-none-linux-gnueabi -f ${opkg_conf} remove  -force-depends     libc6 libc6-dev glibc-extra-nss libgcc1 linux-libc-headers-dev libthread-db1 sln gettext gettext-dev  libgettextlib libgettextsrc"

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
    --addons)
      addons_src="task-arago-toolchain-tisdk-addons-host";
      addons_bin="task-arago-tisdk-addons";
      addons="yes";
      shift;
      ;;
    --dsp)
      dsp_src="task-arago-toolchain-tisdk-dsp-host";
      dsp_bin="task-arago-tisdk-dsp";
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

# check if toolchain path is defined.
test -z $TOOLCHAIN_PATH && usage $0

# check if install_dir variable is set.
test -z $install_dir && usage $0
execute "mkdir -p $install_dir"

# verify if cdrom has architecture specific opkg.conf.
verify_cdrom

# prepare installation.
prepare_install

# create software manifest header.
mkdir -p $install_dir/docs
sw_manifest_header > ${install_dir}/docs/software_manifest.htm

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
echo "Install  $install_dir $bsp_bin $addons_bin $multimedia_bin $graphics_bin $dsp_bin"
fakeroot install_rootfs.sh $install_dir $bsp_bin $addons_bin $multimedia_bin $graphics_bin $dsp_bin 

tar zxf `ls -1 ${install_dir}/filesystem/*.tar.gz` -C $install_dir/filesystem --wildcards *.control*
generate_sw_manifest "Packages installed on the target:" "$install_dir/filesystem" >> ${install_dir}/docs/software_manifest.htm
rm -rf ${install_dir}/filesystem/usr

# copy original image on filesystem directory.
cp deploy/images/$machine/*.tar.gz ${install_dir}/filesystem

generate_sw_manifest "Packages installed on arago-sdk host side:" "$install_dir/linux-devkit" >> ${install_dir}/docs/software_manifest.htm

generate_sw_manifest "Packages installed on arago-sdk target side:" "$install_dir/linux-devkit/arm-none-linux-gnueabi" >> ${install_dir}/docs/software_manifest.htm

# add manifest footer.
sw_manifest_footer >> ${install_dir}/docs/software_manifest.htm


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
 
