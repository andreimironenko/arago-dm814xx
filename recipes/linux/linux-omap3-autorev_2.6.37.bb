require linux-omap3.inc

BRANCH = "refs/heads/master"

SRCREV = "${AUTOREV}"

MACHINE_KERNEL_PR_append = "+gitr${SRCPV}"
