#!/bin/sh

cwd=`dirname $0`
. $cwd/common.sh
prompt="TI8148_EVM#"

#Memory address for loading the images
loadaddr=0x81000000

#offset to partition for u-boot.min.nand in NAND 
uboot_min_offset=0x0

#offset to partition for kernel in NAND 
kernel_offset=0x00280000

#size of the partition for uboot.min.sd
partition_uboot_min=0x00020000

#size of the partition for uboot.bin
partition_uboot_bin=0x00240000

#size of the partition for kernel
partition_kernel=0x00440000

#size of the u-boot.min.nand with a buffer of 4KB along the size of default image
uboot_min_size=0x00018000

#size of the u-boot.bin with a buffer of 4KB along the size of default image
uboot_bin_size=0x0002e800

#size of the uImage with a buffer of 4KB along the size of default image
kernel_size=0x00275000

do_expect() 
{
    echo "expect {" >> $3
    check_status
    echo "    $1" >> $3
    check_status
    echo "}" >> $3
    check_status
    echo $2 >> $3
    check_status
    echo >> $3
}

echo
echo "--------------------------------------------------------------------------------"
echo "This step will create a minicom script for flashing images to NAND"
echo

echo "Would you like to flash the kernel image to NAND (y/n)?"

read -p "[ y ] " kernel
echo

if [ ! -n "$kernel" ]; then
    kernel="y"
fi

if [ "$kernel" == "y" ]; then
    cfg="u-boot_uImage-nand"
else 
    cfg="u-boot-nand"
fi

flashfile=setup_$cfg.minicom
flashfilepath=$cwd/../$flashfile
   
if [ -f $flashfilepath ]; then
    echo "Deleting the old $flashfile"
    rm -f $flashfilepath 
    check_status
fi

timeout=300
echo "timeout $timeout" >> $flashfilepath
echo "verbose on" >> $flashfilepath
echo >> $flashfilepath
do_expect "\"$prompt\"" "send \"mmc rescan 0\"" $flashfilepath   
do_expect "\"$prompt\"" "send \"mw.b $loadaddr 0xFF $partition_uboot_min\"" $flashfilepath
do_expect "\"$prompt\"" "send \"fatload mmc 0 $loadaddr u-boot.min.nand\"" $flashfilepath
do_expect "\"$prompt\"" "send \"nandecc hw 2\"" $flashfilepath
do_expect "\"$prompt\"" "send \"nand erase $uboot_min_offset $partition_uboot_min\"" $flashfilepath
do_expect "\"$prompt\"" "send \"nand write $loadaddr $uboot_min_offset $uboot_min_size\"" $flashfilepath
do_expect "\"$prompt\"" "send \"nandecc hw 0\"" $flashfilepath
do_expect "\"$prompt\"" "send \"mw.b $loadaddr 0xFF $partition_uboot_bin\"" $flashfilepath
do_expect "\"$prompt\"" "send \"fatload mmc 0 $loadaddr u-boot.bin\"" $flashfilepath
do_expect "\"$prompt\"" "send \"nandecc hw 0\"" $flashfilepath
do_expect "\"$prompt\"" "send \"nand erase $partition_uboot_min $partition_uboot_bin\"" $flashfilepath
do_expect "\"$prompt\"" "send \"nand write $loadaddr $partition_uboot_min $uboot_bin_size\"" $flashfilepath

if [ "$kernel" = "y" ]; then 
    do_expect "\"$prompt\"" "send \"mw.b $loadaddr 0xFF $partition_kernel\"" $flashfilepath
    do_expect "\"$prompt\"" "send \"fatload mmc 0 $loadaddr uImage\"" $flashfilepath
    do_expect "\"$prompt\"" "send \"nand erase $kernel_offset $partition_kernel\"" $flashfilepath
    do_expect "\"$prompt\"" "send \"nand write $loadaddr $kernel_offset $kernel_size\"" $flashfilepath
fi

do_expect "\"$prompt\"" "! killall -s SIGHUP minicom" $flashfilepath

echo "Successfully wrote $flashfile"
echo
echo "Would you like to run the setup script now (y/n)? This requires you to connect"
echo "the RS-232 cable between your host and EVM as well as your ethernet cable as"
echo "described in the Quick Start Guide. Please ensure that SW2 switch for NAND is"
echo "in ON position and EVM switch settings is for SD boot mode."
echo
echo "Once answering 'y' on the prompt below you will have $timeout seconds to connect"
echo "the board and power cycle it before the setup times out"
echo
echo "Press any key to stop autoboot of the uboot second stage. Once the autoboot is"
echo "stopped, this script will automatically set parameters."
echo
echo "After successfully executing this script, your EVM will be set up. You will be "
echo "able to connect to it by executing 'minicom -w' or if you prefer a windows host"
echo "you can set up Tera Term as explained in the Software Developer's Guide."
echo "If you connect minicom or Tera Term and power cycle the board Linux will boot."
echo
read -p "[ y ] " minicomsetup

if [ ! -n "$minicomsetup" ]; then
    minicomsetup="y"
fi

if [ "$minicomsetup" == "y" ]; then
    pushd $cwd/..
    check_status
    minicom -S $flashfile
    popd >> /dev/null
    check_status
fi

echo "You can manually run minicom in the future with this setup script using: minicom -S $flashfile"
