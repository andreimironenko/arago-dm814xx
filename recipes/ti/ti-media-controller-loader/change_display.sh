#/bin/sh

SCRIPT=/etc/init.d/load-hd-firmware.sh

if [ $# -ne 1 ]
then
	echo "Usage ./change_resolution.sh <720p60|1080i60|1080p30|1080p60>"
	exit 1;
fi

case $1 in
	720p60 )
		echo "==== Changing Resolution to 720p60 ===="
		sed -i -e "s/modprobe vpss.*/modprobe vpss sbufaddr=0x9fb00000 mode=hdmi:720p-60 debug=1/g" \
		       $SCRIPT;;
	1080i60 )
		echo "==== Changing Resolution to 1080i60 ===="
		sed -i -e "s/modprobe vpss.*/modprobe vpss sbufaddr=0x9fb00000 mode=hdmi:1080i-60 debug=1/g" \
		       $SCRIPT;;
	1080p30 )
		echo "==== Changing Resolution to 1080p30 ===="
		sed -i -e "s/modprobe vpss.*/modprobe vpss sbufaddr=0x9fb00000 mode=hdmi:1080p-30 debug=1/g" \
		       $SCRIPT;;
	1080p60 )
		echo "==== Changing Resolution to 1080p60 ===="
		sed -i -e "s/modprobe vpss.*/modprobe vpss sbufaddr=0x9fb00000 mode=hdmi:1080p-60 debug=1/g" \
		       $SCRIPT;;
	* )
		echo "Error: Unsupported Resolution"
		echo "Usage ./change_resolution.sh <720p60|1080i60|1080p30|1080p60>"
		exit -1;;
esac

# Force a VFS sync immediately
sync

echo "==== Please reboot your board NOW! ===="
