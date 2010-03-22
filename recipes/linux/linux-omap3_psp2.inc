SECTION = "kernel"
DESCRIPTION = "Linux kernel for OMAP3 EVM from PSP, based on linux-omap2 kernel"
LICENSE = "GPL"
KERNEL_IMAGETYPE = "uImage"

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/linux-omap3-psp/${MACHINE}/${PSPREL}"

PV = "${KVER}"
PR = "psp${PSPREL}"

COMPATIBLE_MACHINE = "omap3evm"

S = "${WORKDIR}/git"

inherit kernel

require ../ti/ti-staging.inc
require ../ti/ti-paths.inc

do_configure() {
	make omap3_evm_defconfig
}

do_install_append () {
    	install -d ${D}/${installdir}/ti-psp-tree/linux-kernel-source/
	cp -pPrf ${S}/* ${D}/${installdir}/ti-psp-tree/linux-kernel-source/

}
