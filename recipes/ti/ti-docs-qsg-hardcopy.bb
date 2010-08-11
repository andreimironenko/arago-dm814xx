DESCRIPTION = "Package contain Makefile and Rule.make used for building DVSDK components"
LICENSE = "TI"

require ti-paths.inc

QSG_dm3730-am3715-evm = TMS320DM3730_EVM_Quick_Start_Guide.pdf
QSG_omap3evm = OMAP3530_EVM_Quick_Start_Guide.pdf

SRC_URI = "\
	file://${QSG} \
"

PR = "r3"

do_install () {
	install -d ${D}/${installdir}/ti-docs-tree
	install ${WORKDIR}/${QSG} ${D}/${installdir}/ti-docs-tree
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
