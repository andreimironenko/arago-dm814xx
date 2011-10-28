require linux-omap3.inc

DESCRIPTION = "Linux kernel for TI33x EVM from PSP, based on am335x-kernel"

COMPATIBLE_MACHINE = "ti33x"

BRANCH = "master"

SRCREV = "8900fa3b25de9724ee026e4762e0521dfcbc1d27"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "+gitr${SRCPV}"

KVER = ${PV}
PSPREL = "04.06.00.02.sdk"

# Override the SRC_URI from the linux-omap3.inc file
SRC_URI = "git://arago-project.org/git/projects/linux-am33x.git;protocol=git;branch=${BRANCH} \
           file://0001-omap_hsmmc-make-default-dto-value-14.patch \
           file://defconfig"
