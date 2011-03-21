# Arago PSP/Unified image
# gives you an image with basic media libraries

require arago-image.inc

COMPATIBLE_MACHINE = "arago|arago-armv?"

# The size of the uncompressed ramdisk is 32MB
ROOTFS_SIZE = "32768"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-psp \
    "

export IMAGE_BASENAME = "arago-psp-image"
