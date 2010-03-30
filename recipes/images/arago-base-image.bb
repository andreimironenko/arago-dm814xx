# Arago base image
# gives you a small image with package manager

require arago-image.inc

# The size of the uncompressed ramdisk is 9MB
ROOTFS_SIZE = "9216"

IMAGE_INSTALL += "task-arago-base"

export IMAGE_BASENAME = "arago-base-image"
