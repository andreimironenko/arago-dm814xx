require linux-omap3.inc

DESCRIPTION = "Linux kernel for TI33x EVM from PSP, based on am335x-kernel"

COMPATIBLE_MACHINE = "ti33x"

BRANCH = "v3.2-staging"

#DEFAULT_PREFERENCE = "-1"

SRCREV = "${AUTOREV}"
PV = "3.2"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "+gitr${SRCPV}"

KVER = ${PV}
PSPREL = "autorev"

# Override the SRC_URI from the linux-omap3.inc file
SRC_URI = "git://arago-project.org/git/projects/linux-am33x.git;protocol=git;branch=${BRANCH} \
           file://0001-omap_hsmmc-make-default-dto-value-14.patch \
           file://0001-musb-update-PIO-mode-help-information-in-Kconfig.patch \
           file://0001-am335x_evm_defconfig-turn-off-MUSB-DMA.patch \
           file://defconfig"
