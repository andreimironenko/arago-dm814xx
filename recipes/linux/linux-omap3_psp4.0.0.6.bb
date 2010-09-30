require linux-omap3_psp2.inc

DESCRIPTION = "Linux kernel for ti816x devices" 

COMPATIBLE_MACHINE = "ti816x"

PSPREL = "4.0.0.6"
KVER = "2.6.34"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "de5ce6556f41711317a44878fc68839e4d899268"

SRC_URI = "git://arago-project.org/git/projects/linux-omap3.git;protocol=git;branch=ti816x-master \
	file://defconfig"


S = "${WORKDIR}/git"


