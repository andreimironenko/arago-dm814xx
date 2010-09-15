require linux-omap3_psp2.inc

DESCRIPTION = "Linux kernel for ti816x devices" 

COMPATIBLE_MACHINE = "ti816x"

PSPREL = "4.0.0.5"
KVER = "2.6.34"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "046197da00e36d7c7c25c0fb7768ec24db07a82b"

SRC_URI = "git://arago-project.org/git/projects/linux-omap3.git;protocol=git;branch=ti816x-master \
	file://defconfig"


S = "${WORKDIR}/git"


