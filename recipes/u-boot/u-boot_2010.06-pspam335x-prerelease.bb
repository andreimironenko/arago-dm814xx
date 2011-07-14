# NOTE: This recipe pulls from a repository that is only available inside
#       of the TI firewall and cannot currently be built outside of TI.

require recipes/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for TI33x devices"

COMPATIBLE_MACHINE = "ti33x"

BRANCH = "int_am335x_u-boot"

SRCREV = "${AUTOREV}"
PV = "2010.06"
PR = "r0"
PR_append = "+gitr${SRCPV}"

UVER = "2010.06"
PSPREL = "am335x-prerelease"

# NOTE: In order to use this recipe inside the TI firewall you need to add the
#       following line to your ~/.gitconfig file as the first line in the "core"
#       section.
#           gitproxy = none for gitorious.tif.ti.com

SRC_URI = "git://gitorious.tif.ti.com/am335x-linux/am335x-u-boot.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
