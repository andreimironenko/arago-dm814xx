#!/bin/sh

# Default DM365 Memory Map 128 MB
#
# Start Addr    Size    Description
# ----------------------------------------------------------------
# 0x00001000    32K     ARM926 TCM memory used by platinum codec
# 0x80000000    48 MB   Linux
# 0x83000000    12 MB   Video driver memory (Linux)
# 0x83C00000    68 MB   CMEM
# 0x88000000    BOTTOM  ADDRESS
#

# This loadmodules script is provided to support 1920x1080 resolution file based encode/decode DMAI unit test applications

# remove previously loaded cmem to ensure that its using our pool configuration
rmmod cmemk 2>/dev/null

# use heap based allocation 
modprobe cmemk phys_start=0x83C00000 phys_end=0x88000000 allowOverlap=1 phys_start_1=0x00001000 phys_end_1=0x00008000 pools_1=1x28672 useHeapIfPoolUnavailable=1

# minimum pool
#pools=1x384,2x5984,2x3133440,1x16384,1x48952,1x20480,1x60288,1x74,1x28,1x2048,1x6785280,1x146,1x896,1x65536,1x98,1x296,29x56,2x24,1x624,4x62,1x1456,1x18321120,1x65792,5x3523584,1x4194304,1x8355840 allowOverlap=1 phys_start_1=0x00001000 phys_end_1=0x00008000 pools_1=1x28672

modprobe irqk
modprobe edmak
modprobe dm365mmap

rm -f /dev/dm365mmap
mknod /dev/dm365mmap c `awk "\\$2==\"dm365mmap\" {print \\$1}" /proc/devices` 0

