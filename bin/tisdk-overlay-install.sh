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

# Delete this script
rm -f $0

echo "Installation completed!"

exit 0
