#!/bin/sh
#
# manage HDVPSS Display firmware

PATH=$PATH:/usr/share/ti/ti-hdvpss-display-utils

case "$1" in
    start)
        insmod syslink.ko
        sleep 1
        m3_firmware_load.m3 /usr/share/ti/ti-hdvpss-display-utils/ti816x_hdvpss.xem3
        sleep 2
        insmod vpss.ko
        insmod ti81xxfb.ko
        insmod TI81xx_hdmi.ko hdmi_mode=2
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

