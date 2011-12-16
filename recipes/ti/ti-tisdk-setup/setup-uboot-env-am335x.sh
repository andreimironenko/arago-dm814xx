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
echo "be stored in uEnv.txt on the boot partition. U-boot will read this"
echo "file on boot."
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
fi

hasFTDI=`lsusb | grep "0403:a6d0"`
configBB="n"

if [ -n "$hasFTDI" ]
then
    echo ""
    echo ""
    echo "This SDK supports both the AM335x EVM as well as the BeagleBone."
    echo "A BeagleBone has been detected as attached to your host system"
    echo "Are you wanting to configure u-boot for this BeagleBone?  An"
    echo "answer of 'n' will configure u-boot for the AM335x EVM instead"
    read -p "(y/n) " configBB
fi

if [ "$configBB" = "y" ]; then
#The BeagleBone has been detected, write information to uEnv.txt


	if [ "$kernel" -eq "1" ]; then	   
		 if [ "$fs" -eq "1" ]; then
			#TFTP and NFS Boot
			echo "serverip=$ip" > $cwd/uEnv.txt
			echo "rootpath=$rootpath" >> $cwd/uEnv.txt
			echo "bootfile=$uimage" >> $cwd/uEnv.txt
			echo "ip_method=dhcp" >> $cwd/uEnv.txt
			echo "tftp_nfs_boot=echo Booting from network...; dhcp \${loadaddr} \${bootfile}; run net_args; bootm \${loadaddr}" >> $cwd/uEnv.txt
			echo "uenvcmd=run tftp_nfs_boot" >> $cwd/uEnv.txt
		    else
			#TFTP and SD Boot  
			echo "serverip=$ip" > $cwd/uEnv.txt 
			echo "bootfile=$uimage" >> $cwd/uEnv.txt
			echo "ip_method=dhcp" >> $cwd/uEnv.txt
			echo "tftp_sd_boot=run bootargs_defaults; dhcp \${loadaddr} \${bootfile}; run mmc_args; bootm \${loadaddr}" >> $cwd/uEnv.txt
			echo "uenvcmd=run tftp_sd_boot" >> $cwd/uEnv.txt     

		    fi
		else
		    if [ "$fs" -eq "1" ]; then
			#SD and NFS Boot
			echo "serverip=$ip" > $cwd/uEnv.txt
			echo "rootpath=$rootpath" >> $cwd/uEnv.txt
			echo "ip_method=dhcp" >> $cwd/uEnv.txt
			echo "uenvcmd=setenv autoload no; mmc rescan 0; run mmc_load_uimage; run net_args; bootm \${kloadaddr}" >> $cwd/uEnv.txt
		    else
			#SD and SD boot
			echo "ip_method=dhcp" > $cwd/uEnv.txt
			echo "uenvcmd=mmc rescan 0; run mmc_boot" >> $cwd/uEnv.txt
		    fi
	fi



	#Copy uEnv.txt to the mounted /media/boot partition
	if [ ! -d /media/boot ]; then
		echo "The boot partition doesn't appear to be mounted on the host."
		echo "If you're using a virtual machine, please ensure it has been imported to Linux"
		echo "Please ensure the boot partition is mounted and run setup.sh again."
		read -p "Press Enter to exit" pressEnter
		exit
	fi
	
	echo "Copying uEnv.txt to boot partition..."

	#Let's check to make sure it's /media/boot/ and not /media/boot_
	boots=`ls -1 /media/ | grep boot`
	start_heres=`ls -1 /media/ | grep START_HERE`
	if [ ! "$boots" = "boot" -o ! "$start_heres" = "START_HERE" ]; then
		echo "There are multiple boot and/or START_HERE partions mounted in /media/."
		echo "Please unmount these partitions, reset the board, and run setup.sh again."
		read -p "Press Enter to exit" pressEnter
		exit
	fi

	cp $cwd/uEnv.txt /media/boot/
	sync
	sync



	ftdiInstalled=`lsmod | grep ftdi_sio`
	if [ -z "$ftdiInstalled" ]; then
	#Add the ability to regconize the BeagleBone as two serial ports
		echo "Finishing install by adding drivers for Beagle Bone..."
		sudo modprobe -q ftdi_sio vendor=0x0403 product=0xa6d0

		#Create uDev rule
		echo "# Load ftdi_sio driver including support for XDS100v2." > $cwd/99-custom.rules
		echo "SYSFS{idVendor}=="0403", SYSFS{idProduct}=="a6d0", \\" >>  $cwd/99-custom.rules
		echo "RUN+=\"/sbin/modprobe -q ftdi_sio vendor=0x0403 product=0xa6d0\"" >> $cwd/99-custom.rules
		sudo cp $cwd/99-custom.rules /etc/udev/rules.d/
		rm $cwd/99-custom.rules
	fi

	port=`dmesg | grep FTDI | grep "tty" | tail -1 | grep "attached" |  awk '{ print $NF }'`
	while [ -z "$port" ]
	do
		sleep 1
		port=`dmesg  | grep FTDI | grep "tty" | tail -1 | grep "attached" |  awk '{ print $NF }'`
	done

	#Change minicom to accurately reflect the bone
	minicomcfg=${HOME}/.minirc.dfl
	echo "pu port             /dev/$port
	pu baudrate         115200
	pu bits             8
	pu parity           N
	pu stopbits         1
	pu minit
	pu mreset
	pu mdialpre
	pu mdialsuf
	pu mdialpre2
	pu mdialsuf2
	pu mdialpre3
	pu mdialsuf3
	pu mconnect
	pu mnocon1          NO CARRIER
	pu mnocon2          BUSY
	pu mnocon3          NO DIALTONE
	pu mnocon4          VOICE
	pu rtscts           No" | tee $minicomcfg > /dev/null
	check_status

	echo
	echo
	echo "--------------------------------------------------------------------------------"
	echo "uEnv.text has been saved to the boot partition. uEnv.txt contains:"
	cat $cwd/uEnv.txt
	echo
	echo "On the next boot, the BeagleBone will boot with these settings." 
	echo "Would you like to restart now (y/n)?"
	read -p "[ y ]" restartNow

	if [ ! -n "$restartNow" ]; then
  	  restartNow="y"
	fi

	if [ "$restartNow" = "y" ]; then
		echo "timeout 300" > $cwd/resetBoard.minicom
		echo "verbose on" >> $cwd/resetBoard.minicom
		echo "send \"\""  >> $cwd/resetBoard.minicom
		do_expect "\"am335x-evm login: \"" "send \"root\"" $cwd/resetBoard.minicom
		echo "send \"init 6\""  >> $cwd/resetBoard.minicom
		do_expect "\"Restarting system.\"" "! killall -s SIGHUP minicom" $cwd/resetBoard.minicom
	        # Change directory into cwd because minicom does not like . in the
	        # path to the script
	        cd $cwd
		minicom -w -S resetBoard.minicom
	        cd -
	
	fi

	echo "--------------------------------------------------------------------------------"
	minicom -w	

else
#This is an AM335x EVM and thus has a NAND. Flash information to NAND.


	echo "timeout 300" > $cwd/setupBoard.minicom
	echo "verbose on" >> $cwd/setupBoard.minicom
	do_expect "\"stop autoboot:\"" "send \" \"" $cwd/setupBoard.minicom
	if [ "$kernel" -eq "1" ]; then	
	    	if [ "$fs" -eq "1" ]; then
			#TFTP and NFS Boot
			do_expect "\"U-Boot#\"" "send \"setenv serverip $ip\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv rootpath $rootpath\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv bootfile $uimage\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv ip_method dhcp\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv bootcmd 'setenv autoload no;dhcp \"\$\{bootfile\}\";tftp \"\$\{loadaddr\}\" \"\$\{bootfile\}\";run net_args;bootm \"\$\{loadaddr\}\"'\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"saveenv\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"boot\"" $cwd/setupBoard.minicom
	   	else
			#TFTP and SD Boot  
			do_expect "\"U-Boot#\"" "send \"setenv serverip $ip\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv bootfile $uimage\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv ip_method dhcp\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv bootcmd 'run bootargs_defaults; setenv autoload no; dhcp \"\$\{bootfile\}\"; tftp \"\$\{loadaddr\}\" \"\$\{bootfile\}\"; run mmc_args; bootm \"\$\{loadaddr\}\"'\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"saveenv\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"boot\"" $cwd/setupBoard.minicom
		fi    
	else
		if [ "$fs" -eq "1" ]; then
			#SD and NFS Boot
			do_expect "\"U-Boot#\"" "send \"setenv serverip $ip\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv rootpath $rootpath\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv bootfile uImage\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv ip_method dhcp\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv mmc_load_uimage 'mmc rescan; fatload mmc \"\$\{mmc_dev\}\" \"\$\{kloadaddr\}\" \"$\{bootfile\}\"'\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv bootcmd 'setenv autoload no; run mmc_load_uimage; run net_args; bootm \"\$\{kloadaddr\}\"'\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"saveenv\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"boot\"" $cwd/setupBoard.minicom
		    else
			#SD and SD boot.	
			do_expect "\"U-Boot#\"" "send \"setenv ip_method dhcp\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv bootfile uImage\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"setenv bootcmd 'mmc rescan 0; run mmc_boot'\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"saveenv\"" $cwd/setupBoard.minicom
			do_expect "\"U-Boot#\"" "send \"boot\"" $cwd/setupBoard.minicom
		    
		fi
	fi
	echo "! killall -s SIGHUP minicom" >> $cwd/setupBoard.minicom

	echo "--------------------------------------------------------------------------------"
	echo "Would you like to create a minicom script with the above parameters (y/n)?"
	read -p "[ y ] " minicom
	echo

	if [ ! -n "$minicom" ]; then
	    minicom="y"
	fi	
	
	if [ "$minicom" = "y" ]; then
	
	    echo -n "Successfully wrote "
    	    readlink -m $cwd/setupBoard.minicom
	
	    echo "Would you like to run the setup script now (y/n)? This requires you to connect"
	    echo "the RS-232 cable between your host and EVM as well as your ethernet cable as"
	    echo "described in the Quick Start Guide. Once answering 'y' on the prompt below"
	    echo "you will have 300 seconds to connect the board and power cycle it"
	    echo "before the setup times out"
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
      
            if [ "$minicomsetup" = "y" ]; then
              cd $cwd
	      minicom -w -S setupBoard.minicom
              cd -
	    fi

            echo "You can manually run minicom in the future with this setup script using: minicom -S $cwd/setupBoard.minicom"
            echo "--------------------------------------------------------------------------------"

      fi
fi
