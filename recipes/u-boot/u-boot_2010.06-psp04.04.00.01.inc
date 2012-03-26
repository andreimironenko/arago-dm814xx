require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti816x|ti814x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI81XXPSP_04.04.00.01"

UVER = "2010.06"
PSPREL = "04.04.00.01"

UBOOT_MAKE_TARGET = "u-boot.ti"

INC_PR = "r3"

SRC_URI += "file://0001-ti814x-ti816x-changes-in-makefile-for-supporting-log.patch \
            file://0002-ti814x-ti816x-header-file-for-the-logo.patch \
            file://0003-ti814x-ti816x-files-for-firmware-settings-during-u-b.patch \
            file://0004-ti814x-setting-the-board-for-displaying-logo-at-u-bo.patch \
            file://0005-ti814x-adding-configs-for-ti814x-for-logo-support.patch \
            file://0006-ti816x-setting-the-board-for-display-of-logo.patch \
            file://0007-ti816x-added-configs-for-logo-support-at-u-boot.patch \
            file://0008-ti814x-ti816x-adding-the-vpdma-firmware-1B2-for-logo.patch \
"
