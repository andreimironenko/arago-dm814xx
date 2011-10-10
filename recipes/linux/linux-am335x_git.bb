require linux-omap3.inc

DESCRIPTION = "Linux kernel for TI33x EVM from PSP, based on am335x-kernel"

COMPATIBLE_MACHINE = "ti33x"

BRANCH = "master"

SRCREV = "${AUTOREV}"
PV = "3.0+3.0.1-rc3"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "+gitr${SRCPV}"

KVER = ${PV}
PSPREL = "-prerelease"

# Override the SRC_URI from the linux-omap3.inc file
SRC_URI = "git://arago-project.org/git/projects/linux-am33x.git;protocol=git;branch=${BRANCH} \
           file://0001-am335x-evm-Add-support-for-Beaglebone-Board.patch \
           file://0002-Enable-bit15-in-the-maccontrol-register-for-100Mbps.patch \
           file://defconfig"
