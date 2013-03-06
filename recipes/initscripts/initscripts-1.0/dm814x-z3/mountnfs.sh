#!/bin/sh

#Check if HTC is on
ping -w 1 htc

RESULT=$?

if [ $RESULT -ne 0 ] ; then
   echo "HTC is not present, stop mounting"
   exit 0
fi

if [ ! -d /usr/share/data ] ; then
  mkdir -p /usr/share/data
fi

busybox mount -t nfs htc:/htc/media /usr/share/data

exit 0