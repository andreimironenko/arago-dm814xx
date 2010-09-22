DESCRIPTION = "C6000 SYSLINK  Software"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/index.html"

SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/syslink_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/${PV}/exports/syslink_${PV}.tar.gz;name=syslinktarball"

DEPENDS = "ti-sysbios ti-cgt6x ti-cgt-tms470 ti-xdctools"

do_install() {
    install -d ${D}${SYSLINK_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${SYSLINK_INSTALL_DIR_RECIPE}
}

