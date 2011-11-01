#!/bin/sh
# Turn on the VBUS power when the device is removed so that devices will
# be found again when inserted into the host port.  The sleep delays
# are to make sure that the VBUS power is turned on with the right timing.

sleep 1
echo F > /proc/driver/musb_hdrc.1
sleep 1
echo F > /proc/driver/musb_hdrc.1
