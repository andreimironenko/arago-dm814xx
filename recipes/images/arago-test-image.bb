# Arago System Test image
# gives you test applications

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

# The size of the uncompressed ramdisk is 80MB
ROOTFS_SIZE = "81920"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-test \
    "

export IMAGE_BASENAME = "arago-test-image"



