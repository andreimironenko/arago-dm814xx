#! /usr/bin/env bash
#
#  (C) Copyright 2010 Texas Instruments Inc.  All rights reserved.
#
#  Author: Brijesh Singh, Texas Instruments Inc.
#
#  Adapted for EZSDK by Siddharth Heroor.
#
#  Script to install TI SDK on User Platform
#

VERSION="1.0"

#
# Display program usage
#
usage()
{
  echo "
Usage: `basename $1` [options] --machine <machine_name> --toolchain <toolchain-location>

  --help                Print this help message
  --toolchain           Where is toolchain installed
  --machine             Name of the machine we are installing
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
update_rules_make ()
{
  echo "Updating Rules.make"

  sed -i -e s:EZSDK_INSTALL_DIR=.*:EZSDK_INSTALL_DIR=$install_dir: $install_dir/Rules.make 
  sed -i -e s:CSTOOL_DIR=.*:CSTOOL_DIR=$toolchain_dir: $install_dir/Rules.make 
}

#
# update linux-devkit
#
update_linux_devkit ()
{
  echo "Updating Linux Devkit environment-setup"
  sed -i -e s:SDK_PATH=.*:SDK_PATH=$install_dir/linux-devkit: $install_dir/linux-devkit/environment-setup
  sed -i -e s:TOOLCHAIN_PATH=.*:TOOLCHAIN_PATH=$toolchain_dir: $install_dir/linux-devkit/environment-setup

  echo "Updating Linux Devkit libtoolize "
  sed -i -e s:=.*/linux-devkit:=$install_dir/linux-devkit:g $install_dir/linux-devkit/bin/libtoolize

}

#
# update dsp-devkit
#
update_dsp_devkit ()
{
  echo "Updating DSP Devkit environment-setup"
  cgtools=`grep -e "CODEGEN.*dsp-devkit" Rules.make | sed -e "s/^.*dsp-devkit\///"`
  xdctools=`grep -e "XDC.*component-sources" Rules.make | sed -e "s/^.*DIR)\///"`
  sed -i -e s:DSP_DEVKIT_PATH=.*:DSP_DEVKIT_PATH=$install_dir/dsp-devkit: $install_dir/dsp-devkit/environment-setup
  sed -i -e s:\<__cgt6x__\>:$cgtools: $install_dir/dsp-devkit/environment-setup
  sed -i -e s:\<__xdctools__\>:$install_dir/$xdctools: $install_dir/dsp-devkit/environment-setup

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
    --machine)
      shift
      machine=$1;
      shift;
      ;;
    --toolchain)
      shift
      toolchain_dir=$1;
      shift;
      ;;
     *)
      shift;
      ;;
  esac
done

# check if machine and toolchain directory is defined.
test -z $machine && usage $0
test -z $toolchain_dir && usage $0


install_dir=$(cd $(dirname $0) && pwd)
echo  $install_dir

cd $install_dir
for i in *.tar.gz
do
    echo "Extracting tarball $i"
    tar -zxf $i
    rm -rf $i
done

update_rules_make
update_linux_devkit
update_dsp_devkit

# Delete this script
rm -f $0

echo "Installation completed!"

exit 0
