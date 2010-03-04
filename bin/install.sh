#! /usr/bin/env bash
#
#  (C) Copyright 2010 Texas Instrument Inc.  All rights reserved.
#
#  Author: Brijesh Singh, Texas Instrunents Inc.
#
#  Script to install TI SDK
#

VERSION="1.0"
SDK_VERSION="<version>"

#
# Display program usage
#
usage()
{
  echo "
Usage: `basename $1` [options] --machine <machine_name> <install_dir>

  --help                Print this help message
  --graphics            Install graphics packages
  --bsp                 Install board support packages
  --dsp                 Install c64p dsp packages
  --multimedia          Install multimedia packages
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
  if [ ! -f arch.conf ]; then
    echo "ERROR: arch.conf does not exist in current working directory"
    exit 1;
  fi

  if [ ! -f ti-tisdk-tools*.ipk ]; then
    echo "ERROR: ti-tisdk-tools*.ipk does not exist is cwd"
    exit 1;
  fi
}

#
# execute command
#
execute ()
{
  echo "Executing $*"
  $* 2>/dev/null
  if [ $? -ne 0 ]; then
    echo "ERROR: failed to execute $*"
    exit 1;
  fi

}

#
# install ipk's
# 
ipk_install()
{
  if [ ! -d $1 ]; then
    echo "$1 does not exist"
    exit 1;
  fi

  mkdir -p $root_dir/usr/lib/opkg
  for i in `ls -1 $1/*ipk`; do
    echo "Installing $i"
      opkg-cl -o ${root_dir} -f arch.conf install --force-depends $i >/dev/null
  done
}

#
# Extract tar balls from LSP directory
#
bsp_install()
{
  # install ipks
  ipk_install $1

  # extract filesystem first
  if [ -f $1/arago-*image-*.tar.gz ]; then
    mkdir -p $root_dir/filesystem
    rootfs="`ls -1 $1/arago-*.tar.gz`"
    execute "tar zxf ${rootfs}  -C $root_dir/filesystem"
  else
    echo "ERROR: failed to find root filesystem image"
    exit 1
  fi

  # extract lsp source
  if [ -f $1/linux-*staging*.tar.gz ]; then
    mkdir -p $root_dir/psp/linux-kernel-source
    rootfs="`ls -1 $1/linux-*staging*.tar.gz`"
    execute "tar zxf ${rootfs}  -C $root_dir/psp/linux-kernel-source"
  else
    echo "ERROR: failed to find linux kernel tarball"
    exit 1
  fi
}


#
# install packages
#
start_install()
{
  test ! -z $graphics && ipk_install graphics
  test ! -z $bsp && bsp_install bsp
  test ! -z $dsp && ipk_install dsp
  test ! -z $multimedia && ipk_install multimedia
  ipk_install .
}

#
# update sdk Rules.make
#
update_rules_make()
{
  for i in ${root_dir}/usr/lib/opkg/info/*.control; do
    # we are not greping package name because the name contains ti-*-sourcetree
    name="`cat $i | grep OE | awk {'print $2'} | cut -f2-5 -d-`"

    # in some case version will be 1:xxxx and we need to remove 1: part 
    version="`cat $i | grep Version | awk {'print $2'} | cut -f2 -d: | \
          cut -f1 -d-`"

    # update rules.make
    sed -i -e s/\<__${name}__\>/${name}_${version}/g \
     $root_dir/usr/share/ti/Rules.make
  done

  sed -i -e s=\<__kernel__\>=psp/linux-kernel-source/git= \
    $root_dir/usr/share/ti/Rules.make
  sed -i -e s=\<__SDK__INSTALL_DIR__\>=${root_dir}= \
    $root_dir/usr/share/ti/Rules.make
}

#
# move sourcetree in top label directory to give dvsdk finishing
#
move_to_root_dir()
{
  for i in ${root_dir}/usr/lib/opkg/info/*.control; do
    # we are not greping package name because the name contains ti-*-sourcetree
    name="`cat $i | grep OE | awk {'print $2'} | cut -f2-5 -d-`"

    # in some case version will be 1:xxxx and we need to remove 1: part 
    version="`cat $i | grep Version | awk {'print $2'} | cut -f2 -d: | \
          cut -f1 -d-`"

    # move source from pkginstall_dir/ti-$name-tree to 
    # $ROOT_DIR/$name_$version to present older dvsdk style
    if [ -d ${root_dir}/usr/share/ti/ti-$name-tree ]; then
      mv ${root_dir}/usr/share/ti/ti-$name-tree ${root_dir}/${name}_${version}
    fi
  done
  if [ -d ${root_dir}/usr/share/ti/ti-psp-tree ]; then
    mv ${root_dir}/usr/share/ti/ti-psp-tree/* ${root_dir}/psp
    rm -rf ${root_dir}/usr/share/ti/ti-psp-tree
  fi

  mv ${root_dir}/usr/share/ti/* ${root_dir}/
  rm -rf $root_dir/usr
}

#
# Generate software manifest file
#
generate_sw_manifest()
{
echo "
<h2><u>$1</u></h2>
<table border=1 cellspacing=2 cellpadding=2>
<tr bgcolor=#c0c0c0  color=white><td><b>Package Name</b></td><td><b>Version</b></td><td><b>License</b></td><td><b>Category</b></td></tr>
"

  for i in $2/usr/lib/opkg/info/*.control; do
    package="`cat $i | grep Package: | awk {'print $2'}`"
    version="`cat $i | grep Version: | awk {'print $2'}`"
    section="`cat $i | grep Section: | awk {'print $2'}`"
    license="`cat $i | grep License: | awk {'print $2'}`"
    if [ "$license" = "unknown" ]; then
      highlight="bgcolor=yellow"
    elif [ "$license" = "GPLv3" ]; then
      highlight="bgcolor=red"
    else
      highlight=""
    fi

    echo "
<tr><td>${package} </td><td> ${version}</td> <td $highlight> ${license} </td> <td> $section</td></tr>
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
$machine SDK ${SDK_VERSION} Installation Summary 
</TITLE>
</HEAD>
<BODY>
<h1><CENTER> $machine SDK ${SDK_VERSION} Installation Manifest </CENTER></h1>
"
}

#
# sw manifest footer
#
sw_manifest_footer()
{
echo "
<hr>
<right> <i><font color=grey>Auto generated by TI SDK installer on `date`</i> </right></font>
</body>
</html>
"
}


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
      graphics="yes";
      shift;
      ;;
    --bsp)
      bsp="yes";
      shift;
      ;;
    --dsp)
      dsp="yes";
      shift;
      ;;
    --multimedia)
      multimedia="yes"
      shift;
      ;;
    --machine)
      shift
      machine=$1;
      shift;
      ;;
     *)
      root_dir=$1;
      shift;
      ;;
  esac
done

test -z $machine && usage $0
if [ ! -d $PWD/$machine ];  then
  echo "$PWD/$machine does not exist"
  exit 1;
fi

# check if run as root
if [ "$UID" -ne "0" ]; then
  echo "You must be root to run this script!"
  exit 1;
fi

cd $PWD/$machine

# install packages
test -z $root_dir && usage $0
verify_cdrom
start_install 
update_rules_make

# create software manifest docs
mkdir -p $root_dir/docs
sw_manifest_header > ${root_dir}/docs/software_manifest.htm
generate_sw_manifest "Packages installed on the host machine:" $root_dir >> ${root_dir}/docs/software_manifest.htm;
generate_sw_manifest "Packages installed on the target filesystem:" $root_dir/filesystem >> ${root_dir}/docs/software_manifest.htm;
sw_manifest_footer >> ${root_dir}/docs/software_manifest.htm

# move sourcetree in dvsdk style
move_to_root_dir

exit 0
 
