require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti814x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI814XPSP_04.01.00.06.patch1"

UVER = "2010.06"
PSPREL = "04.01.00.06.patch1"

UBOOT_MAKE_TARGET = "u-boot.ti"

INC_PR = "r1"

SRC_URI += "file://0001-ti816x-C-file-for-logo-and-colorbar.patch \
            file://0002-ti814x-file-to-set-the-board-for-vpss.patch \
            file://0003-ti816x-files-to-set-the-hdmi-settings-and-board-for-.patch \
            file://0004-ti814x-ti816x-header-file-for-logo-and-colorbar.patch \
            file://0005-ti816x-header-files-for-hdmi-settings.patch \
            file://0006-ti814x-ti816x-changes-in-Makefile-for-colorbar-ad-lo.patch \
"
