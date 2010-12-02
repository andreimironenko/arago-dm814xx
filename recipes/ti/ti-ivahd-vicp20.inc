DESCRIPTION = "TI IVAHD HDVICP 2.0 Configuration software"
HOMEPAGE = "http://www.ti.com/"

SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/ivahd_hdvicp20api_${PV}_production"

SRC_URI = " http://install.source.dir.local/ivahd_hdvicp20api_${PV}_production.tar;name=hdvicp20"

do_compile() {
    :
}

do_install() {
    install -d ${D}${HDVICP20_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${HDVICP20_INSTALL_DIR_RECIPE}
}
