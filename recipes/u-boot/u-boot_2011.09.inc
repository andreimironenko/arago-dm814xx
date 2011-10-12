# Using relative path here causes bitbake to search in
# BBPATH for the first instance of u-boot.inc rather
# than just within the current directory.
require recipes/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for TI33X devices"

COMPATIBLE_MACHINE = "ti33x"

PACKAGE_ARCH = "${MACHINE_ARCH}"

BRANCH = "int_am335xpsp_04.06.00.01-v2011.09-for-sdk-05.03.00.00"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/trini/u-boot.git;protocol=git;branch=${BRANCH}"

SRCREV = "456880b203bc44c025c374c98fa327b804a9cf64"
PR = "r2"
PR_append = "+gitr${SRCPV}"

UVER = "2011.09"
PSPREL = "sdk"

UBOOT_MACHINE = "am335x_evm_config"
