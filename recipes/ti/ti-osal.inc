DESCRIPTION = "C6000 Operating System Abstraction Layer (OSAL) Software"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/osal/index.html"

SECTION = "devel"
LICENSE = "BSD" 

require ti-paths.inc
require ti-staging.inc

PR = "r2"
PVExtra = ""

S = "${WORKDIR}/osal_${PV}${PVExtra}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/osal/${PV}/exports/osal_${PV}.tar.gz;name=osaltarball"

DEPENDS = "ti-sysbios ti-cgt6x ti-xdctools"

do_compile() {
    cd ${S}/packages/linuxdist/build && \
        make CROSS_COMPILE="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
             OSAL_INSTALL_DIR="../../../"

    cd ${S}/packages/linuxdist/cstubs && \
        make CROSS_COMPILE="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
             OSAL_INSTALL_DIR="../../../"
}

do_install() {
    install -d ${D}${OSAL_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${OSAL_INSTALL_DIR_RECIPE}
}

