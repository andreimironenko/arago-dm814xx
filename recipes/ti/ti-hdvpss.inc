DESCRIPTION = "TI HDVPSS"
SECTION = "devel"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/HDVPSS_${PV}"

SRC_URI = "http://install.source.dir.local/HDVPSS_${PV}.zip;name=hdvpss"

do_install() {
    install -d ${D}${HDVPSS_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${HDVPSS_INSTALL_DIR_RECIPE}
}

