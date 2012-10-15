require linux-omap3.inc

COMPATIBLE_MACHINE = "ti811x"

KVER = "2.6.37"
PSPREL = "04.07.00.02"

BRANCH = "ti81xx-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "56268cb58c92191eca12c7d9b663946f5f0e2c18"

SRC_URI += "file://0001-VideoDecoder-enabling-TVP5158-for-video-capture.patch \
            file://0001-V4L2-Rebase-patch-for-enabling-scaled-video-streams.patch \
"
