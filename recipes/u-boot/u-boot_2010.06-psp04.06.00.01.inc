# Using relative path here causes bitbake to search in
# BBPATH for the first instance of u-boot.inc rather
# than just within the current directory.
require recipes/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for TI33X devices"

COMPATIBLE_MACHINE = "ti33x"

PACKAGE_ARCH = "${MACHINE_ARCH}"

BRANCH = "int_am335xpsp_04.06.00.01"

S = "${WORKDIR}/git"

# NOTE: In order to use this recipe inside the TI firewall you need to add the
#       following line to your ~/.gitconfig file as the first line in the "core"
#       section.
#           gitproxy = none for gitorious.tif.ti.com
SRC_URI = "git://gitorious.tif.ti.com/am335x-linux/am335x-u-boot.git;protocol=git;branch=${BRANCH}"

SRCREV = "${AUTOREV}"
PV = "2010.06"
PR = "r1"
PR_append = "+gitr${SRCPV}"

UVER = "2010.06"
PSPREL = "04.06.00.01"

UBOOT_MAKE_TARGET = "u-boot.ti"
