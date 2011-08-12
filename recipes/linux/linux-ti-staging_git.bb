require linux-omap3.inc

DESCRIPTION = "Linux kernel for Omap3 from TI Staging tree"

BRANCH = "refs/heads/master"

PV = "2.6.39"
KVER = "2.6.39"

SRCREV = "${AUTOREV}"

MACHINE_KERNEL_PR_append = "+gitr${SRCPV}"


