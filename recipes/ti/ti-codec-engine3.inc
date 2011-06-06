DESCRIPTION = "Codec Engine v3.0 for TI ARM/DSP processors"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ce"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

PVExtra = ""

S = "${WORKDIR}/codec_engine_${PV}${PVExtra}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ce/${PV}/exports/codec_engine_${PV},lite.tar.gz;name=cetarball "

DEPENDS = "ti-framework-components ti-xdais ti-xdctools ti-linuxutils ti-sysbios ti-cgt6x ti-osal ti-ipc ti-syslink ti-edma3lld"

do_install() {
        # Install/Stage the Source Tree
        install -d ${D}${CE_INSTALL_DIR_RECIPE}
        cp -pPrf ${S}/* ${D}${CE_INSTALL_DIR_RECIPE}
}
