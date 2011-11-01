SECTION = "kernel"
DESCRIPTION = "Linux kernel for OMAP3 EVM from PSP, based on linux-omap3 kernel"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "uImage"

inherit kernel
require copy-defconfig.inc
require setup-defconfig.inc

SRC_URI = "git://arago-project.org/git/projects/linux-omap3.git;protocol=git;branch=ti81xx-master \
	file://defconfig"

# Requires support for SOC_FAMILY matching in COMPATIBLE_MACHINE
COMPATIBLE_MACHINE = "omap3"

S = "${WORKDIR}/git"
