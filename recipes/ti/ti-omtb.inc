DESCRIPTION = "TI OMX Test Bench"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

PR = "r2"

S = "${WORKDIR}/omtb_${PV}${PVExtra}"

SRC_URI = "http://install.source.dir.local/omtb_${PV}${PVExtra}.tar.gz;name=omtb \
           file://unpause_omx.c \
"

DEPENDS_dm816x-custom = "ti-omx"
DEPENDS_dm814x-custom = "ti-omx"
DEPENDS_dm816x-evm = "ti-omx-libs alsa-lib"
DEPENDS_dm814x-evm = "ti-omx-libs alsa-lib"

EXTRA_OEMAKE = "ROOTDIR=${S} \
        EZSDK_INSTALL_DIR=${DVSDK_INSTALL_DIR} \
        OMTB_ROOT=${S} \
        OMX_INSTALL_DIR=${OMX_INSTALL_DIR}/packages \
        fc_PATH=${FC_INSTALL_DIR} \
        ce_PATH=${CE_INSTALL_DIR} \
        osal_PATH=${OSAL_INSTALL_DIR} \
        linuxutils_PATH=${LINUXUTILS_INSTALL_DIR} \
        ipc_PATH=${IPC_INSTALL_DIR} \
        syslink_PATH=${SYSLINK_INSTALL_DIR} \
        xdc_PATH=${XDC_INSTALL_DIR} \
        lindevkit_PATH=${STAGING_DIR_TARGET}/usr \
        rtp_PATH=${STAGING_DIR_TARGET}/usr \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
"

do_compile() {
    oe_runmake DEST_ROOT=${S}/pre-built \
               dm816xbm
  
    cp ${WORKDIR}/unpause_omx.c ${S}
    ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gcc -o unpause_omx.xv5T unpause_omx.c
}

do_install() {
    install -d ${D}/${installdir}/ti-omtb
    install ${S}/pre-built/dm816xbm/bin/ti816x-evm/* ${D}/${installdir}/ti-omtb
    install ${S}/unpause_omx.xv5T ${D}/${installdir}/ti-omtb
    install ${S}/packages/ti/sdo/omtb/scripts/* ${D}/${installdir}/ti-omtb
}

FILES_${PN} = "${installdir}/ti-omtb/*"
INSANE_SKIP_${PN} = True
