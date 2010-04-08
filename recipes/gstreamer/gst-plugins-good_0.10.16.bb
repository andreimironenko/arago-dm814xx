require gst-plugins.inc

SRC_URI += "\
file://0001-Fix-OSS-support-to-handle-all-supported-sample-rates.patch;patch=1 \

file://0002-Support-for-non-standard-colorspaces-and-custom-V4L2.patch;patch=1 \

file://0003-Clean-up-platform-specific-set-up-for-calls-to-S_FMT.patch;patch=1 \

file://0004-Fix-for-streaming-encode-with-rtph264pay.patch;patch=1  \

file://0005-Add-support-for-NV16-colorspace.patch;patch=1 \

file://0006-Set-bytesperline-and-sizeimage-before-calling-the-VI.patch;patch=1 \

file://0007-Update-gst_v4l2_get_norm-to-handle-DM6467T-properly.patch;patch=1 \

file://0008-Add-V4L2-ioctl-calls-to-initialize-DM6467T-driver-pr.patch;patch=1 \

file://0009-Disable-video-device-polling-by-default-on-DM6467T.patch;patch=1 \

"

PR = "r2"

OE_ALLOW_INSECURE_DOWNLOADS = "1"

DEPENDS += "gst-plugins-base"

PLATFORM_dm6446        = "dm6446"
PLATFORM_dm6467        = "dm6467"
PLATFORM_omap3         = "omap3530"
PLATFORM_dm355         = "dm355"
PLATFORM_dm365         = "dm365"
PLATFORM_omapl137      = "omapl137"
PLATFORM_omapl138      = "omapl138"
PLATFORM              ?= "<UNDEFINED_PLATFORM>"
CPPFLAGS_append = " -DPlatform_${PLATFORM}"

EXTRA_OECONF += "--disable-esd --disable-annodex --disable-x " 

