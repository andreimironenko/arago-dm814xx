require linux-omap3.inc

DESCRIPTION = "Linux kernel for Omap3 from Upstream staging tree"

BRANCH = "refs/heads/master"

PV = "2.6.39"
KVER = "2.6.39"

MACHINE_KERNEL_PR_append = "+gitr${SRCPV}"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/tmlind/linux-omap-2.6.git;protocol=git;branch=${BRANCH}"

SRCREV = "${AUTOREV}"

do_setup_defconfig() {
        :
}

do_configure() {
        make omap2plus_defconfig
}

