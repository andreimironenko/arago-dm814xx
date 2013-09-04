#!/bin/sh
#
# hostname.sh   Set hostname.
#
# Version: Hanover Displays, amironenko@hanoverdisplays.com      
#
ethaddr=`cat /sys/class/net/eth0/address`

if test -f /etc/hostname; then
   hostname=`cat /etc/hostname`
   hostname="${hostname}-${ethaddr}"
else
   hostname="${ethaddr}"
fi

   hostname ${hostname}

