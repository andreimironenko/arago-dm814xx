require linux-omap3.inc

DESCRIPTION = "Linux kernel for Omap3 from mainline kernel.org"

BRANCH = "refs/heads/master"

SRCREV = "${AUTOREV}"
PV = "2.6.39"
KVER = "2.6.39"

MACHINE_KERNEL_PR_append = "+gitr${SRCPV}"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git;branch=${BRANCH}"

do_setup_defconfig() {
        : 
}       

do_configure() {
        make omap2plus_defconfig
}

