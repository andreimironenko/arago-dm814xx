require linux-omap3.inc

COMPATIBLE_MACHINE = "ti811x"

KVER = "2.6.37"
PSPREL = "04.07.00.01"

BRANCH = "ti81xx-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2.6.37_TI811XPSP_ENG5_REL"

SRC_URI += "file://0001-ti81XX-VPSS-Fix-some-errors-introduced-by-025c0be49a.patch \
            file://0001-VideoDecoder-enabling-TVP5158-for-video-capture.patch \
            file://ti811x-kernel-nand-change-to-hamming-ecc-scheme.patch;striplevel=0 \
"
