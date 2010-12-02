DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/omx_${PV}${PVExtra}"

SRC_URI = "http://install.source.dir.local/omx_${PV}${PVExtra}.tar.gz;name=omx"

do_install() {
    install -d ${D}${OMX_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${OMX_INSTALL_DIR_RECIPE}
}

