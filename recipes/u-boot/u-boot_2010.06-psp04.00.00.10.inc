require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti816x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI816XPSP_04.00.00.10"
SRC_URI += "file://0001-TI816x-Add-default-support-for-800Mhz.patch;apply=yes"

UVER = "2010.06"
PSPREL = "04.00.00.10"

UBOOT_MAKE_TARGET = "u-boot.ti"

INC_PR = "r1"
