require linux-omap3.inc

DESCRIPTION = "Linux kernel for TI33x EVM from PSP, based on am335x-kernel"

COMPATIBLE_MACHINE = "ti33x"

BRANCH = "master"

SRCREV = "717aec7087b8bd4dc15a1d8a2f5cd6473ea51cd1"

# Append to the MACHINE_KERNEL_PR so that we can tell what commit id the kernel
# was built with.
MACHINE_KERNEL_PR_append = "+gitr${SRCPV}"

KVER = "3.1.0"
PSPREL = "04.06.00.03.sdk"

# Override the SRC_URI from the linux-omap3.inc file
SRC_URI = "git://arago-project.org/git/projects/linux-am33x.git;protocol=git;branch=${BRANCH} \
           file://0001-omap_hsmmc-make-default-dto-value-14.patch \
           file://0001-musb-update-PIO-mode-help-information-in-Kconfig.patch \
           file://defconfig"
