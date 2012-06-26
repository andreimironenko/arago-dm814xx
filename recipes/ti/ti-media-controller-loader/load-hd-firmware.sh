#!/bin/sh
#
# manage DSP, HDVICP2 and HDVPSS Engine firmware

PATH=$PATH:/usr/share/ti/ti-media-controller-utils
DSP_ID=0
HDVICP2_ID=1
HDVPSS_ID=2
      
case "$1" in
    start)
        /usr/share/ti/j5eco-tvp5158/decoder_init
        sleep 2
        echo "Loading HDVPSS Firmware"
        modprobe syslink
        sleep 2
        until [[ -e /dev/syslinkipc_ProcMgr && -e /dev/syslinkipc_ClientNotifyMgr ]]
        do                                                
            sleep 0.5
        done
        /usr/share/ti/syslink-examples/TI811X/messageq/slaveloader_debug startup VPSS-M3 /usr/share/ti/ti-media-controller-utils/ti811x_hdvpss.xem3 start
        sleep 2
        modprobe vpss sbufaddr=0x9fd00000 mode=hdmi:720p-60 i2c_mode=1 debug=1
        sleep 2
        modprobe ti81xxfb vram=0:16M,1:16M,2:6M  debug=1
        sleep 2
        modprobe ti81xxvo video1_numbuffers=3 video2_numbuffers=3 debug=1
        sleep 2
        modprobe tvp7002 debug=1
        sleep 2
        modprobe ti81xxvin debug=1
        sleep 2
        modprobe sii9022a
        sleep 2
        modprobe tlc59108
        sleep 2
        echo "Loading DSP Firmware"
        firmware_loader $DSP_ID /usr/share/ti/rpe/dm81xx_c6xdsp_debug.xe674 start
        sleep 2
      ;;

    stop)
        echo "Unloading DSP Firmware"
        firmware_loader $DSP_ID /usr/share/ti/rpe/dm81xx_c6xdsp_debug.xe674 stop
        rmmod tlc59108
        rmmod sii9022a
        rmmod ti81xxvin
        rmmod tvp7002
        rmmod ti81xxvo
        rmmod ti81xxfb
        rmmod vpss
        echo "Unloading HDVPSS Firmware"
        firmware_loader $HDVPSS_ID /usr/share/ti/ti-media-controller-utils/ti811x_hdvpss.xem3 stop
        rm /tmp/firmware.$HDVPSS_ID
        rmmod syslink
      ;;
    *)
        echo "Usage: /etc/init.d/load-hd-firmware.sh {start|stop}"
        exit 1
        ;;
esac

exit 0
