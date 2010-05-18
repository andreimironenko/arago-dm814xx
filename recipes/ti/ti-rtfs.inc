DESCRIPTION = "DSP/BIOS Real-Time File System (RTFS)"
HOMEPAGE = "http://www.sanb.design.ti.com//tisb_releases//RTFS/"

SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/rtfs_${PV}"

SRC_URI = "http://www.sanb.design.ti.com//tisb_releases//RTFS/${PV}/exports/rtfs_${PV}.tar.gz;name=rtfstarball"

DEPENDS = "ti-dspbios ti-edma3lld ti-biospsp"
DEPENDS += "ti-cgt6x ti-xdctools"

do_install() {
    install -d ${D}${RTFS_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${RTFS_INSTALL_DIR_RECIPE}

}

