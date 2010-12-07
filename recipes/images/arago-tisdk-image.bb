# Arago TI-SDK image
# Builds all the components used by TI SDK products in one shot
# Official TI SDK products don't use this image directly, though

require arago-image.inc

COMPATIBLE_MACHINE = "(?!arago)"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-base-tisdk \
    task-arago-tisdk-bsp \
    task-arago-tisdk-graphics \
    task-arago-tisdk-crypto \
    task-arago-tisdk-addons \
    task-arago-tisdk-dsp \
    task-arago-tisdk-multimedia \
    "

export IMAGE_BASENAME = "arago-tisdk-image"
