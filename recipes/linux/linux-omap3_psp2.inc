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
    	install -d ${D}/${LINUXKERNEL_SOURCE_DIR}
	cp -pPrf ${S}/* ${D}/${LINUXKERNEL_SOURCE_DIR}
        cd ${D}/${LINUXKERNEL_SOURCE_DIR} 
        make distclean
}
