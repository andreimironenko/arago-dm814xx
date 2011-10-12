require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti814x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI814XPSP_04.01.00.06"

UVER = "2010.06"
PSPREL = "04.01.00.06"

UBOOT_MAKE_TARGET = "u-boot.ti"

INC_PR = "r1"

SRC_URI += "file://0002-TI814X-Fixed-autoload-boot.scr-issue-in-SD-boot.patch \
"
