#!/bin/sh

# This loadmodules script is provided to support 1920x1080 resolution file based encode/decode DMAI unit test applications

# remove previously loaded cmem to ensure that its using our pool configuration
rmmod cmemk 2>/dev/null

# Pools configuration
modprobe cmemk phys_start=0x83C00000 phys_end=0x88000000 pools=1x384,2x5984,2x3133440,1x16384,1x48952,1x20480,1x60288,1x74,1x28,1x2048,1x6785280,1x146,1x896,1x65536,1x98,1x296,29x56,2x24,1x624,4x62,1x1456,1x18321120,1x65792,5x3523584,1x4194304,1x8355840

modprobe irqk
modprobe edmak
modprobe dm365mmap

rm -f /dev/dm365mmap
mknod /dev/dm365mmap c `awk "\\$2==\"dm365mmap\" {print \\$1}" /proc/devices` 0

