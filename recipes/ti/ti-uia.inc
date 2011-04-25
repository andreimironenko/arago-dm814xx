DESCRIPTION = "TI Unified Instrumentation Architecture for ARM/DSP Devices"
SECTION = "devel"
LICENSE = "BSD" 

require ti-paths.inc
require ti-staging.inc

PR = "r1"
PVExtra = ""

S = "${WORKDIR}/uia_${PV}${PVExtra}"

SRC_URI = "http://www.sanb.design.ti.com//tisb_releases//UIA/${PV}/exports/uia_${PV}${PVExtra}.zip;name=uia"

do_install() {
    install -d ${D}${UIA_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${UIA_INSTALL_DIR_RECIPE}
}
