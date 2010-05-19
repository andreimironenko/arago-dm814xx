DESCRIPTION = "Package contains board utilies for TI platforms"
LICENSE = "TI"

require ti-paths.inc

SRC_URI = "http://arago-project.org/files/releases/davinci-psp_${PV}/sources/flash-utils-davinci.tar.gz;name=boardutilitiestarball"

S = "${WORKDIR}/flash_utils"

do_compile () {
	cd ${S}
}

do_install () {
	install -d ${D}/${installdir}/ti-psp-tree/board-utilities
        cp -pPrf ${S}/* ${D}/${installdir}/ti-psp-tree/board-utilities
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
PACKAGE_STRIP = "no"
INSANE_SKIP_${PN} = True

