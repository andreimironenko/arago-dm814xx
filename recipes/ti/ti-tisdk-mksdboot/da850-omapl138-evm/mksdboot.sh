#! /bin/sh
# Script to create SD card for OMAPL138 plaform.
#
# Author: Brijesh Singh, Texas Instruments Inc.
#
# Licensed under terms of GPLv2

VERSION="0.2"

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
  echo "Script to create bootable SD card for OMAPL138 EVM"
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

if [ ! -f $sdkdir/filesystem/dvsdk-da850-omapl138-evm-rootfs.tar.gz ]; then
  echo "ERROR: failed to find rootfs $sdkdir/filesystem/dvsdk-da850-omapl138-evm-rootfs.tar.gz"
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

execute "dd if=/dev/zero of=$device bs=1024 count=1024"

# get the partition information.
total_size=`fdisk -l $device | grep Disk | awk '{print $5}'`
total_cyln=`echo $total_size/255/63/512 | bc`

# default number of cylinder for first parition
pc1=9

# calculate number of cylinder for the second parition
if [ "$copy" != "" ]; then
 pc2=$((($total_cyln - $pc1) / 2))
fi


{
echo ,$pc1,0x0C,*
if [ "$pc2" != "" ]; then
 echo ,$pc2,,-
 echo ,,,-
else
 echo ,,,-
fi
} | sfdisk -D -H 255 -S 63 -C $total_cyln $device

if [ $? -ne 0 ]; then
    echo ERROR
    exit 1;
fi

echo "Formating ${device}1 ..."
execute "mkfs.vfat -F 32 -n "BOOT" ${device}1"
echo "Formating ${device}2 ..."
execute "mke2fs -j -L "ROOTFS" ${device}2"
if [ "$pc2" != "" ]; then
 echo "Formating ${device}3 ..."
 execute "mke2fs -j -L "INSTALLER" ${device}3"
fi

echo "Extracting filesystem on ${device}2 ..."
execute "mkdir -p /tmp/sdk/$$"
execute "mount ${device}2 /tmp/sdk/$$"
execute "tar zxf $sdkdir/filesystem/dvsdk-da850-omapl138-evm-rootfs.tar.gz -C /tmp/sdk/$$"

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

if [ "$pc2" != "" ]; then
  echo "Copying $copy on ${device}3 ..."
  execute "mount ${device}3 /tmp/sdk/$$"
  execute "cp -ar $copy /tmp/sdk/$$"
  echo "unmounting ${device}3"
  execute "umount /tmp/sdk/$$"
fi

execute "rm -rf /tmp/sdk/$$"
echo "completed!"

