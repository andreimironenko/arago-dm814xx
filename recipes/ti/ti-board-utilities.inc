DESCRIPTION = "Package contains board utilies for TI platforms"
LICENSE = "TI"

require ti-paths.inc

COMPATIBLE_MACHINE = "(dm365-evm|dm355-evm|dm6467-evm)"

SRC_URI = "http://install.source.dir.local/board_utilities_${PV}.tar.gz;name=boardutilitiestarball"

S = "${WORKDIR}/board_utilities"

do_install () {
	install -d ${D}/${installdir}/ti-psp-tree/board-utilities
        cp -pPrf ${S}/* ${D}/${installdir}/ti-psp-tree/board-utilities
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
