DESCRIPTION = "TI OMX Test Bench"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

PV = "01_00_01_02"
PVExtra = ""

S = "${WORKDIR}/omtb_${PV}${PVExtra}"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/OMTB/${PV}/exports/omtb_${PV}.tar.gz;name=omtb \
"

SRC_URI[omtb.md5sum] = "a512a63ff9be63289a201930dfd010dc"
SRC_URI[omtb.sha256sum] = "7785fae685bce2fa17525ba9c944566bf16d023488a47cc3b3cbc0db9ecb1f4d"

DEPENDS = "ti-omx alsa-lib ti-uia"

OMTBTARGET_ti816x = "dm816xbm"
OMTBTARGET_ti814x = "dm814xbm"

OMTBEVM_ti816x = "ti816x-evm"
OMTBEVM_ti814x = "ti816x-evm"

EXTRA_OEMAKE = "ROOTDIR=${S} \
        EZSDK_INSTALL_DIR=${DVSDK_INSTALL_DIR} \
        OMTB_ROOT=${S} \
        OMX_INSTALL_DIR=${OMX_INSTALL_DIR}/packages \
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
"

do_compile() {
    oe_runmake DEST_ROOT=${S}/bin ${OMTBTARGET}
}

do_install() {
    install -d ${D}/${installdir}/ti-omtb
    install ${S}/bin/${OMTBTARGET}/bin/${OMTBEVM}/* ${D}/${installdir}/ti-omtb
    install ${S}/packages/ti/sdo/omtb/scripts/* ${D}/${installdir}/ti-omtb
}

FILES_${PN} = "${installdir}/ti-omtb/*"
INSANE_SKIP_${PN} = True
