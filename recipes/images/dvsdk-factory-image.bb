# DVSDK factory default image
# This gives you a image which can be flashed by board vendors

require arago-image.inc

COMPATIBLE_MACHINE = "(dm365-evm)"

# The size of the uncompressed ramdisk is 120MB
ROOTFS_SIZE_dm365-evm = "122880"

IMAGE_INSTALL += "\
    task-arago-base \
    task-dvsdk-factory \
    "

export IMAGE_BASENAME = "dvsdk-factory-image"
