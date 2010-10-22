#!/bin/sh
#
# manage HDVPSS Display firmware

PATH=$PATH:/usr/share/ti/ti-hdvpss-display-utils

case "$1" in
    start)
        echo "==== Loading SYSLINK ===="
        modprobe syslink
        sleep 1
        echo "==== Loading Display Firmware ===="
        m3_firmware_load.m3 2 /usr/share/ti/ti-hdvpss-display-utils/ti816x_hdvpss.xem3
        sleep 2
        echo "==== Loading Display Drivers ===="
        modprobe vpss mode=hdmi:720p-60,dvo2:720p-60 debug=1
        modprobe ti81xxfb
        modprobe TI81xx_hdmi hdmi_mode=2
        sleep 1
        fbset -depth 32 -rgba 8/16,8/8,8/0,0/0
      ;;
    stop)
        echo "Unloading HDVPSS Firmware"
        # TODO
      ;;
    *)
        echo "Usage: /etc/init.d/load-firmware {start|stop}"
        exit 1
        ;;
esac

exit 0

