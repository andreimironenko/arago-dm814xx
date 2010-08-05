#! /bin/sh
# Script to create DVSDK SD card for DM3730/OMAP3530 plaform.
#
# Author: Brijesh Singh, Texas Instruments Inc.
#
# Licensed under terms of GPLv2
#

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
  echo "Script to create bootable SD card for DM3730/OMAP3530 EVM"
  echo

  exit 0
}

usage ()
{
  echo "
Usage: `basename $1` <options> [ files for install partition ]

Mandatory options:
  --device              SD block device node (e.g /dev/sdd)
  --sdk                 Where is sdk installed ?

Optional options:
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

if [ ! -f $sdkdir/filesystem/dvsdk-dm3730-am3715-evm-rootfs.tar.gz ]; then
  if [ ! -f $sdkdir/filesystem/dvsdk-omap3evm-rootfs.tar.gz ]; then
    echo "ERROR: failed to find rootfs tar in $sdkdir/filesystem "
    exit 1;
  fi
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
total_size=`fdisk -l $device | grep Disk | awk '{print $5}'`
total_cyln=`echo $total_size/255/63/512 | bc`

# default number of cylinder for first parition
pc1=5

# calculate number of cylinder for the second parition
if [ "$copy" != "" ]; then
# hard code to use 110 cylinder so that we can fit both DM3730 and AM3715 
# installer on one 4GB SD card.
  pc2=110
# pc2=$((($total_cyln - $pc1) / 2))
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
execute "mkfs.ext3 -j -L "ROOTFS" ${device}2"
if [ "$pc2" != "" ]; then
 echo "Formating ${device}3 ..."
 execute "mkfs.ext3 -j -L "START_HERE" ${device}3"
fi

echo "Copying u-boot/mlo/uImage on ${device}1"
execute "mkdir -p /tmp/sdk/$$"
execute "mount ${device}1 /tmp/sdk/$$"
execute "cp $sdkdir/psp/prebuilt-images/uImage /tmp/sdk/$$/"
execute "cp $sdkdir/psp/prebuilt-images/u-boot.bin /tmp/sdk/$$/"
execute "cp $sdkdir/psp/prebuilt-images/MLO /tmp/sdk/$$/"
execute "cp $sdkdir/docs/TMS320DM3730_EVM_Quick_Start_Guide.pdf /tmp/sdk/$$/"
execute "cp $sdkdir/bin/top_omap35x_evm.png /tmp/sdk/$$/"
execute "cp $sdkdir/bin/windows_users.htm /tmp/sdk/$$/"
sync


# creating boot.scr
cat <<EOF >/tmp/sdk/$$/boot.cmd
setenv bootargs 'console=ttyS0,115200n8 root=/dev/mmcblk0p2 rw ip=off mem=99M mpurate=1000  omap_vout.vid1_static_vrfb_alloc=y omapfb.mode=dvi:720x480MR-16@60 omapdss.def_disp=lcd rootwait'
fatload mmc 0 80200000 uImage
bootm 80200000
EOF

mkimage -A arm -O linux -T script -C none -a 0 -e 0 -n 'Execute uImage.bin' -d /tmp/sdk/$$/boot.cmd /tmp/sdk/$$/boot.scr

if [ $? -ne 0 ]; then
  echo "Failed to execute mkimage to create boot.scr"
  echo "Execute 'sudo apt-get install uboot-mkimage' to install the package"
  exit 1
fi

sync
echo "unmounting ${device}1"
execute "umount /tmp/sdk/$$"

execute "mount ${device}2 /tmp/sdk/$$"
echo "Extracting filesystem on ${device}2 ..."
rootfs=`ls -1 $sdkdir/filesystem/dvsdk*rootfs.tar.gz`
execute "tar zxf $rootfs -C /tmp/sdk/$$"
sync

# check if we need to create symbolic link for matrix to auto start
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

sync
echo "unmounting ${device}2"
execute "umount /tmp/sdk/$$"

if [ "$pc2" != "" ]; then
  echo "Copying $copy on ${device}3 ..."
  execute "mount ${device}3 /tmp/sdk/$$"
  execute "cp -ar $copy /tmp/sdk/$$"
  execute "cp $sdkdir/bin/setup.htm /tmp/sdk/$$"
  execute "cp $sdkdir/bin/top_omap35x_evm.png /tmp/sdk/$$/"
  execute "cp $sdkdir/docs/TMS320DM3730_EVM_Quick_Start_Guide.pdf /tmp/sdk/$$/"
  sync
  echo "unmounting ${device}3"
  execute "umount /tmp/sdk/$$"
fi

execute "rm -rf /tmp/sdk/$$"
echo "completed!"

