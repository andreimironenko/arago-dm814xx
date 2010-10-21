#! /bin/sh
# Script to create SD card for DM365 plaform.
#
# Author: Brijesh Singh, Texas Instruments Inc.
#

VERSION="0.3"

execute ()
{
    $* >/dev/null
    if [ $? -ne 0 ]; then
        echo
        echo "ERROR: executing $*"
        echo
        exit 1
    fi
}

version ()
{
  echo
  echo "`basename $1` version $VERSION"
  echo "Script to create bootable SD card for DM365 EVM"
  echo

  exit 0
}

usage ()
{
  echo "
Usage: `basename $1` [options] <sdk_install_dir> <device>

  --device              SD block device node (e.g /dev/sdd)
  --sdk                 Where is sdk installed ?
  --copy                Copy file on third partition.
  --version             Print version.
  --help                Print this help message.
"
  exit 1
}

# Process command line...
while [ $# -gt 0 ]; do
  case $1 in
    --help | -h)
      usage $0
      ;;
    --device) shift; device=$1; shift; ;;
    --sdk) shift; sdkdir=$1; shift; ;;
    --copy) shift; copy=$1; shift; ;;
    --version) version $0;;
    *) usage $0;;
  esac
done

test -z $sdkdir && usage $0
test -z $device && usage $0

if [ ! -d $sdkdir ]; then
   echo "ERROR: $sdkdir does not exist"
   exit 1;
fi

if [ ! -f $sdkdir/filesystem/dvsdk-dm365-evm-rootfs.tar.gz ]; then
  echo "ERROR: failed to find rootfs $sdkdir/filesystem/dvsdk-dm365-evm-rootfs.tar.gz"
  exit 1;
fi
 
if [ ! -b $device ]; then
   echo "ERROR: $device is not a block device file"
   exit 1;
fi

if [ "$copy" != "" ]; then
  if [ ! -f $copy ]; then
    echo "ERROR: $copy does not exist"
    exit 1
  fi
fi

echo "************************************************************"
echo "*         THIS WILL DELETE ALL THE DATA ON $device        *"
echo "*                                                          *"
echo "*         WARNING! Make sure your computer does not go     *"
echo "*                  in to idle mode while this script is    *"
echo "*                  running. The script will complete,      *"
echo "*                  but your SD card may be corrupted.      *"
echo "*                                                          *"
echo "*         Press <ENTER> to confirm....                     *"
echo "************************************************************"
read junk

for i in `ls -1 $device?`; do
 echo "unmounting device '$i'"
 umount $i 2>/dev/null
done

# format the SD card
echo "Executing external script to format and create the boot record ..."
execute "cd bin/dm3xx_sd_boot-6.1"
./dm3xx_sd_boot format $device $copy
if [ $? -ne 0 ]; then
    echo "ERROR: failed to execute external script"
    exit 1;
fi

echo "Executing external script to copy kernel/uboot/ubl images ..."
execute "mkdir -p /tmp/sdk/$$"
execute "mount ${device}1 /tmp/sdk/$$"
./dm3xx_sd_boot data /tmp/sdk/$$/dm3xx.dat $sdkdir/psp/board-utilities/DM36x/GNU/ubl/ubl_DM36x_nand.bin $sdkdir/psp/prebuilt-images/u-boot.bin $sdkdir/psp/prebuilt-images/uImage-dm365-evm.bin
 
if [ $? -ne 0 ]; then
    echo "ERROR: failed to execute external script"
    exit 1;
fi
execute "cd .."
execute "umount ${device}1"

echo "Formating ${device}2 ..."
execute "mke2fs -j -L "ROOTFS" ${device}2"
if [ "$copy" != "" ]; then
 echo "Formating ${device}3 ..."
 execute "mke2fs -j -L "INSTALLER" ${device}3"
fi

echo "Extracting filesystem on ${device}2 ..."
execute "mkdir -p /tmp/sdk/$$"
execute "mount ${device}2 /tmp/sdk/$$"
execute "tar zxf $sdkdir/filesystem/dvsdk-dm365-evm-rootfs.tar.gz -C /tmp/sdk/$$"

# check if we need to create symbolic link for matrix 
echo -n "Creating matrix-gui symbolic link..."
if [ -f /tmp/sdk/$$/etc/init.d/matrix-gui ]; then
  if [ -h /tmp/sdk/$$/etc/rc3.d/*matrix* ]; then
    echo " (skipped) "
  else
    ln -s  ../init.d/matrix-gui /tmp/sdk/$$/etc/rc3.d/S99matrix-gui
    ln -s  ../init.d/matrix-gui /tmp/sdk/$$/etc/rc5.d/S99matrix-gui
    echo "done"
  fi
fi

echo "unmounting ${device}2"
execute "umount /tmp/sdk/$$"

if [ "$copy" != "" ]; then
  echo "Copying $copy on ${device}3 ..."
  execute "mount ${device}3 /tmp/sdk/$$"
  execute "cp -ar $copy /tmp/sdk/$$"
  echo "unmounting ${device}3"
  execute "umount /tmp/sdk/$$"
fi

execute "rm -rf /tmp/sdk/$$"
echo "completed!"

