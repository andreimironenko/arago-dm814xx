DESCRIPTION = "DM365 Codecs"
SECTION = "multimedia"

BASE_SRC_URI = "http://install.source.dir.local"
SRC_URI	= "${BASE_SRC_URI}/dm365_codecs_${PV}.tar.gz"

S = "${WORKDIR}/dm365_codecs_${PV}"

require ti-paths.inc

do_compile() {
	echo "Do nothing"
}

do_install () {
     # install mapdmaq on target
     install -d ${D}/${installdir}/codecs

    install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}

do_stage() {
    install -d ${CODEC_INSTALL_DIR}
    cp -pPrf ${S}/* ${CODEC_INSTALL_DIR}
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "${installdir}/codecs"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = True

