#!/bin/sh

cwd=`dirname $0`
. $cwd/common.sh

do_expect() {
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
echo "This step will set up the u-boot variables for booting the EVM."
echo "Becuase the not all AM335x devices have a NAND, the u-boot variables will"
echo "be stored in uEnv.txt on the boot partition. U-boot will read this" echo "file on boot."
echo

ipdefault=`ifconfig | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1 }'`
platform=`grep PLATFORM= $cwd/../Rules.make | cut -d= -f2`


echo "Autodetected the following ip address of your host, correct it if necessary"
read -p "[ $ipdefault ] " ip
echo

if [ ! -n "$ip" ]; then
    ip=$ipdefault
fi

if [ -f $cwd/../.targetfs ]; then
    rootpath=`cat $cwd/../.targetfs`
else
    echo "Where is your target filesystem extracted?"
    read -p "[ ${HOME}/targetNFS ]" rootpath

    if [ ! -n "$rootpath" ]; then
        rootpath="${HOME}/targetNFS"
    fi
    echo
fi

uimage="uImage-""$platform"".bin"
uimagesrc=`ls -1 $cwd/../board-support/prebuilt-images/$uimage`
uimagedefault=`basename $uimagesrc`


echo "Select Linux kernel location:"
echo " 1: TFTP"
echo " 2: SD card"
echo
read -p "[ 1 ] " kernel

if [ ! -n "$kernel" ]; then
    kernel="1"
fi

echo
echo "Select root file system location:"
echo " 1: NFS"
echo " 2: SD card"
echo
read -p "[ 1 ] " fs

if [ ! -n "$fs" ]; then
    fs="1"
fi

if [ "$kernel" -eq "1" ]; then
    echo
    echo "Available kernel images in /tftproot:"
    for file in /tftpboot/*; do
        basefile=`basename $file`
        echo "    $basefile"
    done
    echo
    echo "Which kernel image do you want to boot from TFTP?"
    read -p "[ $uimagedefault ] " uimage

    if [ ! -n "$uimage" ]; then
        uimage=$uimagedefault
    fi


    if [ "$fs" -eq "1" ]; then
	#TFTP and NFS Boot
        echo "serverip=$ip" > uEnv.txt
	echo "rootpath=$rootpath" >> uEnv.txt
	echo "bootfile=$uimage" >> uEnv.txt
	echo "ip_method=dhcp" >> uEnv.txt
	echo "uenvcmd=run net_boot" >> uEnv.txt

    else
	#TFTP and SD Boot
	echo "serverip=$ip" > uEnv.txt
	echo "bootfile=$uimage" >> uEnv.txt
	echo "uenvcmd=run bootargs_defaults; setenv autoload no; dhcp; tftp \${loadaddr} \${bootfile}; run mmc_args; bootm \${loadaddr}" >> uEnv.txt

    fi
else
    if [ "$fs" -eq "1" ]; then
	#SD and NFS Boot
        echo "serverip=$ip" > uEnv.txt
	echo "rootpath=$rootpath" >> uEnv.txt
	echo "ip_method=dhcp" >> uEnv.txt
	echo "uenvcmd=setenv autoload no; run mmc_load_uimage; run net_args; bootm \${loadaddr}" >> uEnv.txt
    else
        #SD and SD boot
	echo "uenvcmd=run mmc_boot" > uEnv.txt
    fi
fi

echo
echo "Resulting u-boot variable settings (saved to uEnv.txt):"
echo
cat uEnv.txt


#Create minicom script to parse IP and make uEnv.txt
echo "timeout 300" > $cwd/setupBoard.minicom
echo "verbose on" >> $cwd/setupBoard.minicom
echo "send \"\""  >> $cwd/setupBoard.minicom
do_expect "\"am335x-evm login: \"" "send \"root\"" $cwd/setupBoard.minicom
do_expect "\"root@am335x-evm:~#\"" "send \"ifconfig\"" $cwd/setupBoard.minicom
do_expect "\"root@am335x-evm:~#\"" "send \"cd /media/mmcblk0p1/\"" $cwd/setupBoard.minicom
do_expect "\"root@am335x-evm:/media/mmcblk0p1#\"" "send \"rm uEnv.txt\"" $cwd/setupBoard.minicom

while read line
do
	do_expect "\"root@am335x-evm:/media/mmcblk0p1#\"" "send \"echo \"\\\"$line\\\"\" >> uEnv.txt\"" $cwd/setupBoard.minicom
done < uEnv.txt

do_expect "\"root@am335x-evm:/media/mmcblk0p1#\"" "! killall -s SIGHUP minicom" $cwd/setupBoard.minicom


echo
echo "--------------------------------------------------------------------------------"
echo
read -p "Press enter to complete the install. Minicom will load, followed shortly by Firefox with the Matrix GUI." foo
echo


check_status
minicom -S $cwd/setupBoard.minicom | tee $cwd/../bootLog.txt
check_status


boardIP=`cat $cwd/../bootLog.txt | grep 'inet addr:' | grep -v '127.0.0.1' | cut -d: -f2 | awk '{
	 print $1}'`
firefox $boardIP:8080/ &
#cp uEnv.txt /media/boot

read -p "The board is now ready for it's out-of-box experience. Please explore the Matrix GUI now loaded in Firefox. When you're ready to start development, press enter. When enter is hit, the board will reset and load the settings as defined in the previous steps." foo

#Create minicom file to reset board
echo "timeout 300" > $cwd/resetBoard.minicom
echo "verbose on" >> $cwd/resetBoard.minicom
echo "send \"\""  >> $cwd/resetBoard.minicom
echo "send \"init 6\""  >> $cwd/resetBoard.minicom
do_expect "\"Restarting system.\"" "! killall -s SIGHUP minicom" $cwd/resetBoard.minicom

minicom -w -S $cwd/resetBoard.minicom
rm $cwd/*.minicom
minicom -w



