# Using relative path here causes bitbake to search in
# BBPATH for the first instance of u-boot.inc rather
# than just within the current directory.
require recipes/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for TI33X devices"

COMPATIBLE_MACHINE = "ti33x"

PACKAGE_ARCH = "${MACHINE_ARCH}"

BRANCH = "int_am335xpsp_04.06.00.01-v2011.09-with-spl-30sept"

S = "${WORKDIR}/git"

# NOTE: In order to use this recipe inside the TI firewall you need to add the
#       following line to your ~/.gitconfig file as the first line in the "core"
#       section.
#           gitproxy = none for gitorious.tif.ti.com
SRC_URI = "git://github.com/trini/u-boot.git;protocol=git;branch=${BRANCH}"

SRCREV = "bebf75c52780dfdafbe5b5eb05a7d24579674f9d"
PR = "r0"
PR_append = "+gitr${SRCPV}"

UVER = "2011.09"
PSPREL = "sdk"

UBOOT_MACHINE = "am335x_evm_config"
