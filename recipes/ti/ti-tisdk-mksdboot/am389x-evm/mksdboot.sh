#! /bin/sh
# Script to create SD card for SDK.
#
# Author: Brijesh Singh, Texas Instruments Inc.
# Modified: Greg Turner, Texas Instruments Inc.
#
# Licensed under terms of GPLv2

VERSION="0.5"

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
  echo "Script to create bootable SD card"
  echo

  exit 0
}

usage ()
{
  echo "
Usage: `basename $1` [options] <sdk_install_dir> <device>

Mandatory arguments:
  --sdk                 Where is sdk installed ?
  --device              SD block device node (e.g /dev/sdd)

Options:
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
    --version) version $0;;
    *) copy="$copy $1"; shift; ;;
  esac
done

test -z $sdkdir && usage $0
test -z $device && usage $0

if [ ! -d $sdkdir ]; then
   echo "ERROR: $sdkdir does not exist"
   exit 1;
fi

if [ ! -f $sdkdir/filesystem/*rootfs.tar.gz ]; then
  echo "ERROR: failed to find rootfs $sdkdir/filesystem/*-rootfs.tar.gz"
  exit 1;
fi
 
if [ ! -b $device ]; then
   echo "ERROR: $device is not a block device file"
   exit 1;
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

SIZE=`fdisk -l $device | grep Disk | awk '{print $5}'`

echo DISK SIZE - $SIZE bytes

CYLINDERS=`echo $SIZE/255/63/512 | bc`

sfdisk -D -H 255 -S 63 -C $CYLINDERS $device << EOF
,9,0x0C,*
10,115,,-
126,,,-
EOF

execute "mkfs.vfat -F 32 -n "boot" ${device}1"
execute "mkfs.ext3 -L "rootfs" ${device}2"
execute "mkfs.ext3 -L "START_HERE" ${device}3"



if [ $? -ne 0 ]; then
    echo ERROR
    exit 1;
fi


execute "mkdir -p /tmp/sdk"

echo "Copying uImage on ${device}1"
execute "mkdir -p /tmp/sdk/$$"
execute "mount ${device}1 /tmp/sdk/$$"
execute "cp $sdkdir/psp/prebuilt-images/uImage /tmp/sdk/$$"
execute "cp $sdkdir/psp/prebuilt-images/u-boot.bin /tmp/sdk/$$"
execute "cp $sdkdir/bin/setup.htm /tmp/sdk/$$"
execute "cp $sdkdir/bin/*_evm.png /tmp/sdk/$$/"

if [ ! -r $sdkdir/docs/*Quick_Start_Guide.pdf ]
then
	echo "Quick Start Doesn't exist"
else
	execute "cp $sdkdir/docs/*Quick_Start_Guide.pdf /tmp/sdk/$$/quickstartguide.pdf"
fi

execute "cp $sdkdir/bin/README.boot.scr /tmp/sdk/$$/"

sync
sync
execute "umount /tmp/sdk/$$"


echo "Extracting filesystem on ${device}2 ..."
execute "mkdir -p /tmp/sdk/$$"
execute "mount ${device}2 /tmp/sdk/$$"
execute "tar zxf $sdkdir/filesystem/*rootfs.tar.gz -C /tmp/sdk/$$"
execute "cp $sdkdir/psp/prebuilt-images/uImage /tmp/sdk/$$"
execute "sync"

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

execute "sync"
echo "unmounting ${device}2"
execute "umount /tmp/sdk/$$"

execute "rm -rf /tmp/sdk/$$"
echo "completed!"

