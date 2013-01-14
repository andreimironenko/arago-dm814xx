DESCRIPTION = "OMX Core Shared Library required by GStreamer"
HOMEPAGE = "https://gforge.ti.com/gf/project/gstreamer_ti/"
SECTION = "multimedia"
LICENSE = "LGPL"

require ti-paths.inc
require ti-staging.inc

INC_PR = "r1"

DEPENDS = "ti-omx"

SRC_URI = "file://Makefile \
"

S = ${WORKDIR}/omx-core

COMPATIBLE_MACHINE = "dm816x-evm|dm814x-evm|dm814x-z3"

EXTRA_OEMAKE = FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
         CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
         OSAL_INSTALL_DIR="${OSAL_INSTALL_DIR}" \
         UIA_INSTALL_DIR="${UIA_INSTALL_DIR}" \
         SYSLINK_INSTALL_DIR="${SYSLINK_INSTALL_DIR}" \
         LINUXUTILS_INSTALL_DIR="${LINUXUTILS_INSTALL_DIR}" \
         OMX_INSTALL_DIR="${OMX_INSTALL_DIR}" \
         CROSS_COMPILE="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}"

do_prepsources() {
    mkdir ${S}
    cp ${WORKDIR}/Makefile ${S}
}
addtask prepsources after do_unpack before do_create_srcipk

do_install() {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN}     += "${libdir}"
