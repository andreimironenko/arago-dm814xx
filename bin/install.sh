#! /usr/bin/env bash
#
#  (C) Copyright 2010 Texas Instrument Inc.  All rights reserved.
#
#  Author: Brijesh Singh, Texas Instrunents Inc.
#
#  Script to install TI SDK
#

VERSION="1.0"

#
# Display program usage
#
usage()
{
  echo "
Usage: `basename $1` [options] --machine <machine_name> <install_dir>

  --help                Print this help message
  --graphics            Install graphics packages
  --lsp                 Install linux support packages
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
lsp_install()
{
  # extract filesystem first - this requires root access
  mkdir -p $root_dir/filesystem
  tar zxf lsp/arago-base-image-$machine.tar.gz  -C $root_dir/filesystem
}


#
# install packages
#
start_install()
{
  test ! -z $graphics && ipk_install graphics
  test ! -z $lsp && lsp_install
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
  sed -i -e s=\<__SDK__INSTALL_DIR__\>=${root_dir}= \
    $root_dir/usr/share/ti/Rules.make
  sed -i -e s/\<__PLATFORM__\>/${machine}/g \
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
  mv ${root_dir}/usr/share/ti/* ${root_dir}/
  rm -rf $root_dir/usr
}

#
# Generate software manifest file
#
generate_sw_manifest()
{
  printf "\n+------------------------------------------+----------------------------------------------------------------------------------+------------+--------------------------------+"
  printf "\n| %-40s | %-80s | %-10s | %-30s |" "Package" "Version" "License" "Section"
  printf "\n+------------------------------------------+----------------------------------------------------------------------------------+------------+--------------------------------+"

  for i in $1/usr/lib/opkg/info/*.control; do
    package="`cat $i | grep Package: | awk {'print $2'}`"
    version="`cat $i | grep Version: | awk {'print $2'}`"
    section="`cat $i | grep Section: | awk {'print $2'}`"
    license="`cat $i | grep License: | awk {'print $2'}`"

    printf "\n| %-40s | %-80s | %-10s | %-30s |" "$package" "$version" "$license" "$section"
  done
  printf "\n+------------------------------------------+----------------------------------------------------------------------------------+------------+--------------------------------+"
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
    --lsp)
      lsp="yes";
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
echo "Host SDK summary" > ${root_dir}/docs/software_manifest.txt
generate_sw_manifest $root_dir >> ${root_dir}/docs/software_manifest.txt;

echo >> ${root_dir}/docs/software_manifest.txt
echo >> ${root_dir}/docs/software_manifest.txt
echo >> ${root_dir}/docs/software_manifest.txt
echo "Target filesystem summary" >> ${root_dir}/docs/software_manifest.txt
generate_sw_manifest $root_dir/filesystem >> ${root_dir}/docs/software_manifest.txt;

# move sourcetree in dvsdk style
move_to_root_dir

exit 0
 
