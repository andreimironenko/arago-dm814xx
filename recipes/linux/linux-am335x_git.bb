# NOTE: This recipe pulls from a repository that is only available inside
#       of the TI firewall and cannot currently be built outside of TI.
require linux-omap3.inc

DESCRIPTION = "Linux kernel for TI33x EVM from PSP, based on am335x-kernel"

COMPATIBLE_MACHINE = "ti33x"

BRANCH = "int_am335xpsp_04.06.00.01"
#BRANCH = "master"

SRCREV = "${AUTOREV}"
PV = "2.6.38"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "+gitr${SRCPV}"

KVER = "2.6.38"
PSPREL = "-prerelease"

# NOTE: In order to use this recipe inside the TI firewall you need to add the
#       following line to your ~/.gitconfig file as the first line in the "core"
#       section.
#           gitproxy = none for gitorious.tif.ti.com

# Override the SRC_URI from the linux-omap3.inc file
SRC_URI = "git://gitorious.tif.ti.com/am335x-linux/am335x-kernel.git;protocol=git;branch=${BRANCH} \
           file://defconfig"
