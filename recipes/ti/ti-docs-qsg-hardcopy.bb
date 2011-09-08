DESCRIPTION = "Package contain Makefile and Rule.make used for building DVSDK components"
LICENSE = "TI"

require ti-paths.inc

QSG_dm37x-evm = TMS320DM3730_EVM_Quick_Start_Guide.pdf
QSG_omap3evm = OMAP3530_EVM_Quick_Start_Guide.pdf
QSG_da850-omapl138-evm = OMAPL138_EVM_Quick_Start_Guide.pdf
QSG_c6a816x-evm = C6A816x_AM389x_EVM_Quick_start_guide.pdf 
QSG_dm816x-evm = DM816x_C6A816x_AM389x_EVM_Quick_start_guide.pdf 
QSG_dm816x-custom = DM816x_Quick_Start_Guide.pdf
QSG_c6a814x-evm = C6A814x_AM387x_EVM_Quick_start_guide.pdf 
QSG_dm814x-evm = DM814x_AM387x_EVM_Quick_start_guide.pdf
QSG_dm814x-custom = DM814x_Quick_Start_Guide.pdf

SRC_URI = "\
	file://${QSG} \
"

PR = "r6"

do_install () {
	install -d ${D}/${installdir}/ti-docs-tree
	install ${WORKDIR}/${QSG} ${D}/${installdir}/ti-docs-tree
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
