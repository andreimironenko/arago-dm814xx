require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti816x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI816XPSP_04.00.01.13.patch1"

UVER = "2010.06"
PSPREL = "04.00.01.13.patch1"

UBOOT_MAKE_TARGET = "u-boot.ti"

INC_PR = "r2"

SRC_URI += "file://0001-ti816x-ti814x-C-files-for-register-settings-on-the-b.patch \
            file://0002-ti814x-ti816x-header-file-for-logo-support-in-u-boot.patch \
            file://0003-ti814x-added-configs-for-displaying-logo.patch \
            file://0004-ti816x-added-configs-for-displaying-logo.patch \
            file://0005-ti814x-ti816x-changes-in-Makefile-to-compile-the-fil.patch \
            file://0006-ti816x-adding-config-CONFIG_TI81XX_VIDEO-for-logo.patch \
"
