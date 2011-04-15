DESCRIPTION = "UIA"

SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc

PR = "r1"

S = "${WORKDIR}/uia_${PV}${PVExtra}"

SRC_URI = "http://www.sanb.design.ti.com//tisb_releases//UIA/${PV}/exports/uia_${PV}${PVExtra}.zip;name=uia"

do_install() {
    install -d ${D}${UIA_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${UIA_INSTALL_DIR_RECIPE}
}
