#!/bin/sh

#Check if HTC is on

while true;  do
	ping -w 1 htc
	RESULT=$?

	if [ $RESULT -ne 0 ] ; then
		echo "HTC is not present, stop mounting"
		if [ "$HTC_MOUNT_BG" -ne "1" ] ; then
			echo "Start mountnfs.sh in the background and exit"
			export HTC_MOUNT_BG=1
			nohup /etc/init.d/mountnfs.sh  >/dev/null &
			exit 0
		else
			echo "Sleep for the next 15 seconds..."
			sleep 15
			continue;
		fi
		
	else
		#HTC found, now we can try to mount it!
		break;
	fi
done

if [ ! -d /usr/share/data ] ; then
 mkdir -p /usr/share/data
fi

if [ ! -d /media/update ] ; then
 mkdir -p /media/update
fi


busybox mount -t nfs htc:/htc/media /usr/share/data
busybox mount -t nfs htc:/mnt/usb1  /media/update


exit 0