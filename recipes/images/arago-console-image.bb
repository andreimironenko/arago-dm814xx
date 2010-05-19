# Arago console image
# gives you an image with basic media libraries

require arago-image.inc

COMPATIBLE_MACHINE = "(dm365-evm|dm6446-evm|dm355-evm|da830-omapl137-evm|dm6467-evm|da850-omapl138-evm|omap3evm|arago)"

# The size of the uncompressed ramdisk is 32MB
ROOTFS_SIZE = "32768"

# DM646x have many modules, bump to 40MB
ROOTFS_SIZE_dm6467 = "40960"

# Double beagleboard/overo ramdisk size, due to gazillions of kernel modules
ROOTFS_SIZE_beagleboard = "81920"
ROOTFS_SIZE_overo = "81920"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
	psplash-ti \
    "

export IMAGE_BASENAME = "arago-console-image"
