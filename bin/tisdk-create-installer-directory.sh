#! /usr/bin/env bash
#
#  (C) Copyright 2010 Texas Instrument Inc.  All rights reserved.
#
#  Author: 
# 
#  Adapted from tisdk-install.sh by Brijesh Singh
#  for EZSDK by Siddharth Heroor, Texas Instruments Inc.
#
#  Script to install TI SDK
#

VERSION="1.0"
TISDK_NAME="ezsdk"
TISDK_PACKAGES=""

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
  --toolchain           Where is toolchain installed
  --arago               Location of arago-install directory containing sdk-cdrom
  --sdk-version         Version of the EZSDK
  
  This will install into --arago directory with the name ti-ezsdk_<machine>_<sdk-version>
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
      TISDK_PACKAGES="${TISDK_PACKAGES} --graphics"
      shift;
      ;;
    --bsp)
      TISDK_PACKAGES="${TISDK_PACKAGES} --bsp"
      shift;
      ;;
    --crypto)
      TISDK_PACKAGES="${TISDK_PACKAGES} --crypto"
      shift;
      ;;
    --addons)
      TISDK_PACKAGES="${TISDK_PACKAGES} --addons"
      shift;
      ;;
    --dsp)
      TISDK_PACKAGES="${TISDK_PACKAGES} --dsp"
      shift;
      ;;
    --multimedia)
      TISDK_PACKAGES="${TISDK_PACKAGES} --multimedia"
      shift;
      ;;
    --machine)
      shift
      machine=$1;
      shift;
      ;;
    --sdk-version)
      shift
      sdk_version=$1;
      shift;
      ;;
    --arago)
      shift
      arago_dir=$1;
      shift;
      ;;
    --toolchain)
      shift
      TOOLCHAIN_PATH=$1;
      shift;
      ;;
  esac
done

if [ ! -e $arago_dir ]; then
    echo "$arago_dir directory does not exist"
    exit 1
fi

if [ ! -e $arago_dir/sdk-cdrom/install.sh ]; then
    echo "$arago_dir does not contain the sdk-cdrom directory"
    exit 1
fi

sdk_install_path="${arago_dir}/ti-${TISDK_NAME}_${machine}_${sdk_version}"

mkdir -p $sdk_install_path
cd $arago_dir/sdk-cdrom
./install.sh $TISDK_PACKAGES --toolchain $TOOLCHAIN_PATH --machine $machine $sdk_install_path

exit 0
 
