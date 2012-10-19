require linux-omap3.inc

COMPATIBLE_MACHINE = "ti811x"

KVER = "2.6.37"
PSPREL = "04.07.00.02"

BRANCH = "ti81xx-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2.6.37_TI811XPSP_04.07.00.02.patch1"

SRC_URI += "file://0001-VideoDecoder-enabling-TVP5158-for-video-capture.patch \
"
