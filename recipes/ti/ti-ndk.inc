DESCRIPTION = "C6000 Network Developer's Kit (NDK) Software"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ndk/index.html"

SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ndk/${PV}/exports/ti_ndk_src_${PV_short}.tar;name=ndktarball"

DEPENDS = "ti-dspbios ti-cgt6x ti-xdctools"

do_install() {
    install -d ${D}${NDK_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/ti ${D}${NDK_INSTALL_DIR_RECIPE}

}

