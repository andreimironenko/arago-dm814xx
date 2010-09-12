#!/bin/sh

# Default Memory Map
#
# Start Addr    Size    Description
# -------------------------------------------
# 0x80000000    60 MB   Linux
# 0x83C00000    68 MB   CMEM

rmmod cmemk 2>/dev/null

# Pools configuration
modprobe cmemk phys_start=0x83C00000 phys_end=0x88000000 pools=2x384,1x16384,1x2688,1x20480,3x74,1x60288,2x28,1x147048,1x10240,1x896,1x65536,1x663552,1x9175376,4x24,1x282624,2x5984,1x58144,1x1024,1x48952,1x464,2x16768,1x1251264,1x30720,1x65792,8x774144,1x173712,1x146,1x98,2x296,50x56,2x86,1x624,1x3328,1x518912,4x62,1x242,4x663552,1x1637824,14x1645056,1x16538976,4x1382400

modprobe irqk 
modprobe edmak
modprobe dm365mmap

rm -f /dev/dm365mmap
mknod /dev/dm365mmap c `awk "\\$2==\"dm365mmap\" {print \\$1}" /proc/devices` 0

