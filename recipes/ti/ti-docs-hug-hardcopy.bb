DESCRIPTION = "Package contain hardware manual pdf"
LICENSE = "TI"

require ti-paths.inc

QSG_dm37x-evm = AM_DM_3730_HUG.pdf
QSG_omap3evm = OMAP35x_EVM_Hardware_User_Guide.pdf

SRC_URI = "\
	file://${QSG} \
"

PR = "r2"

do_install () {
	install -d ${D}/${installdir}/ti-docs-tree
	install ${WORKDIR}/${QSG} ${D}/${installdir}/ti-docs-tree
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"

