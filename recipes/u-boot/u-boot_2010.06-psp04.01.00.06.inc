require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti814x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI814XPSP_04.01.00.06.patch1"

UVER = "2010.06"
PSPREL = "04.01.00.06.patch1"

UBOOT_MAKE_TARGET = "u-boot.ti"

INC_PR = "r1"

SRC_URI += "file://0001-ti814x-changes-for-u-boot-color-bar.patch \
            file://0002-ti814x-removed-the-function-ti814x_disable.patch \
"
