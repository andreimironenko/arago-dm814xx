# Using relative path here causes bitbake to search in
# BBPATH for the first instance of u-boot.inc rather
# than just within the current directory.
require recipes/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for ARM MPU devices"

COMPATIBLE_MACHINE = "ti33x|am37x-evm|am3517-evm|beagleboard"

PACKAGE_ARCH = "${MACHINE_ARCH}"

BRANCH ?= "master"

S = "${WORKDIR}/git"

SRC_URI = "git://arago-project.org/git/projects/u-boot-am33x.git;protocol=git;branch=${BRANCH}"

SRCREV = "${AUTOREV}"
INC_PR = "r10"

UVER = "2011.09"

UBOOT_MACHINE_ti33x = "am335x_evm_config"
UBOOT_MACHINE_am3517-evm = "am3517_evm_config"
UBOOT_MACHINE_am37x-evm = "omap3_evm_config"
UBOOT_MACHINE_beagleboard = "omap3_beagle_config"
