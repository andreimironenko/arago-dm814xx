DESCRIPTION = "Package contain hardware manual pdf"
LICENSE = "TI"

require ti-paths.inc

QSG_dm3730-am3715-evm = AM_DM_3715_HUG_23Jun.pdf

SRC_URI = "\
	file://${QSG} \
"

PR = "r1"

# for now rename the file to DM3730
do_install_dm3730-am3715-evm () {
	install -d ${D}/${installdir}/ti-docs-tree
	install ${WORKDIR}/${QSG} ${D}/${installdir}/ti-docs-tree/DM_3730_HUG.pdf
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"

