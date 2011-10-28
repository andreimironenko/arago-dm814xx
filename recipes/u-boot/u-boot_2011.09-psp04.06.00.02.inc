# Using relative path here causes bitbake to search in
# BBPATH for the first instance of u-boot.inc rather
# than just within the current directory.
require recipes/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for TI33X devices"

COMPATIBLE_MACHINE = "ti33x"

PACKAGE_ARCH = "${MACHINE_ARCH}"

BRANCH = "int_am335xpsp_04.06.00.01-v2011.09-for-sdk-05.03.00.00"

S = "${WORKDIR}/git"

SRC_URI = "git://arago-project.org/git/projects/u-boot-am33x.git;protocol=git;branch=${BRANCH}"

SRCREV = "f63b270e47f62f4d1a05b2001357e215966c6f5a"
PR = "r7"
PR_append = "+gitr${SRCPV}"

UVER = "2011.09"
PSPREL = "04.06.00.02"

UBOOT_MACHINE = "am335x_evm_config"
