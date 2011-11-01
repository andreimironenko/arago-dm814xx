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

SRCREV = "865ecbf67fe4def1f94589e5aa4528ba1f982e78"
PR = "r6"
PR_append = "+gitr${SRCPV}"

UVER = "2011.09"
PSPREL = "04.06.00.01"

UBOOT_MACHINE = "am335x_evm_config"
