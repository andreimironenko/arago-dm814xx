#!/bin/sh 
#
# Copyright (C) $year Texas Instruments Incorporated - http://www.ti.com/
#
# This program is free software; you can redistribute it and/or modify 
# it under the terms of the GNU Lesser General Public License as
# published by the Free Software Foundation version 2.1 of the License.
#
# This program is distributed #as is# WITHOUT ANY WARRANTY of any kind,
# whether express or implied; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# Lesser General Public License for more details.

# remove previously loaded cmem to ensure that its using our pool configuration
rmmod cmemk 2>/dev/null

# use heap based allocation 
modprobe cmemk phys_start=0x86300000 phys_end=0x87200000  allowOverlap=1 useHeapIfPoolUnavailable=1

# min pool
# pools=20x4096,8x131072,4x829440,1x5250000,1x1429440,1x256000 allowOverlap=1

modprobe dsplinkk
modprobe lpm_omap3530
modprobe sdmak

rm -f /dev/dsplink
mknod /dev/dsplink c `awk "\\$2==\"dsplink\" {print \\$1}" /proc/devices` 0

