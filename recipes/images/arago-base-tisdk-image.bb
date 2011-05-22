# Arago TI SDK base image
# gives you an image with Qt/E and other common packages shared by all TI SDKs.

require arago-image.inc

COMPATIBLE_MACHINE = "omap3|ti816x|dm365|omapl138|ti814x|omap4"

IMAGE_INSTALL += "\
    task-arago-base \
    task-arago-console \
    task-arago-base-tisdk \
    "

export IMAGE_BASENAME = "arago-base-tisdk-image"

# NLCP supports both station and AP modes. 
# don't start hostapd automatically when the system boot up.
ROOTFS_POSTPROCESS_COMMAND += "find ${IMAGE_ROOTFS}/etc/rc* -name *hostapd* | xargs -r rm"

