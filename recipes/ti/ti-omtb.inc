DESCRIPTION = "TI OMX Test Bench"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

PR = "r2"

PVExtra = ""
S = "${WORKDIR}/omtb_${PV}${PVExtra}"

SRC_URI = "http://software-dl.ti.com:82/dsps/dsps_public_sw/omtb/${PV}/exports/omtb_${PV}.tar.gz;name=omtb \
"

DEPENDS = "ti-omx alsa-lib ti-uia"

OMTBEVM_ti816x = "ti816x-evm"
OMTBEVM_ti814x = "ti814x-evm"

EXTRA_OEMAKE = "ROOTDIR=${S} \
        EZSDK_INSTALL_DIR=${DVSDK_INSTALL_DIR} \
        OMTB_ROOT=${S} \
        PLATFORM=${OMTBEVM} \
        OMX_INSTALL_DIR=${OMX_INSTALL_DIR}/src \
        fc_PATH=${FC_INSTALL_DIR} \
        ce_PATH=${CE_INSTALL_DIR} \
        osal_PATH=${OSAL_INSTALL_DIR} \
        uia_PATH=${UIA_INSTALL_DIR} \
        linuxutils_PATH=${LINUXUTILS_INSTALL_DIR} \
        ipc_PATH=${IPC_INSTALL_DIR} \
        syslink_PATH=${SYSLINK_INSTALL_DIR} \
        xdc_PATH=${XDC_INSTALL_DIR} \
        lindevkit_PATH=${STAGING_DIR_TARGET}/usr \
        rtp_PATH=${STAGING_DIR_TARGET}/usr \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
        CROSS_COMPILE=${TARGET_PREFIX} \
        TOOLCHAIN_LONGNAME=arm-arago-linux-gnueabi \
        DEST_ROOT=${S}/bin \
        dm81xxbm \
"

do_install() {
    install -d ${D}/${installdir}/ti-omtb
    install ${S}/bin/dm81xxbm/bin/${OMTBEVM}/* ${D}/${installdir}/ti-omtb
    install ${S}/packages/ti/sdo/omtb/scripts/* ${D}/${installdir}/ti-omtb
}

FILES_${PN} = "${installdir}/ti-omtb/*"
INSANE_SKIP_${PN} = True
