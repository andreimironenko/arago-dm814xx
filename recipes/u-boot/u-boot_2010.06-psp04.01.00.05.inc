require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti814x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI814XPSP_04.01.00.05"

UVER = "2010.06"
PSPREL = "04.01.00.05"

UBOOT_MAKE_TARGET = "u-boot.ti"

INC_PR = "r1"
