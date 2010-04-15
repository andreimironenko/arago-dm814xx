# Arago demo image
# gives you an image with basic media libraries

require arago-image.inc

COMPATIBLE_MACHINE = "(dm365-evm|dm6446-evm|dm355-evm|da830-omapl137-evm|dm6467-evm|da850-omapl138-evm|omap3evm|arago)"

# The size of the uncompressed ramdisk is 100MB
ROOTFS_SIZE = "102400"

# DM646x have many modules, bump to 100MB
ROOTFS_SIZE_dm6467 = "102400"

# Double beagleboard/overo ramdisk size, due to gazillions of kernel modules
ROOTFS_SIZE_beagleboard = "81920"
ROOTFS_SIZE_overo = "81920"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-demo \
    "

export IMAGE_BASENAME = "arago-demo-image"
