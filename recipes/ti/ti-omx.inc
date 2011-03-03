DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/omx_${PV}${PVExtra}"

SRC_URI = "http://install.source.dir.local/omx_${PV}${PVExtra}.tar.gz;name=omx"

SYSLINK_ROOT = "${WORKDIR}/syslink_${SYSLINKPV}${SYSLINKPVextra}"
export SYSLINK_ROOT

DEPENDS_ti816x = "ti-sysbios ti-xdctools ti-ipc ti-cgt470 ti-osal ti-syslink"
DEPENDS_ti816x += "ti-ivahd-hdvicp20api ti-codecs-dm816x"
DEPENDS_ti816x += "ti-xdais ti-codec-engine ti-framework-components"
DEPENDS_ti816x += "ti-hdvpss"
DEPENDS_ti816x += "ortp alsa-lib"

DEPENDS_ti814x = "ti-sysbios ti-xdctools ti-ipc ti-cgt470 ti-osal ti-syslink"
DEPENDS_ti814x += "ti-ivahd-hdvicp20api ti-codecs-dm814x"
DEPENDS_ti814x += "ti-xdais ti-codec-engine ti-framework-components"
DEPENDS_ti814x += "ti-hdvpss"
DEPENDS_ti814x += "ortp alsa-lib"

VCTARGET_ti816x = "vc3"
VCTARGET_ti814x = "Cent_vc3"
VCTARGET = "<UNDEFINED>"
VSTARGET_ti816x = "vs2"
VSTARGET_ti814x = "Cent_vs2_hdvpss"
VSTARGET = "<UNDEFINED>"

do_compile() {
    # Build VC3 (Video Conference)
    cd ${S}/packages && make ${VCTARGET} \
        ROOTDIR=${S}/packages \
        INTERNAL_SW_ROOT=${S}/packages \
        EXTERNAL_SW_ROOT=${DVSDK_INSTALL_DIR} \
        bios_PATH=${SYSBIOS_INSTALL_DIR} \
        fc_PATH=${FC_INSTALL_DIR} \
        osal_PATH=${OSAL_INSTALL_DIR} \
        xdais_PATH=${XDAIS_INSTALL_DIR} \
        linuxutils_PATH=${LINUXUTILS_INSTALL_DIR} \
        ce_PATH=${CE_INSTALL_DIR} \
        h264enc_PATH=${CODEC_INSTALL_DIR} \
        h264dec_PATH=${CODEC_INSTALL_DIR} \
        hdvicp20api_PATH=${HDVICP20_INSTALL_DIR} \
        ipc_PATH=${IPC_INSTALL_DIR} \
        syslink_PATH=${SYSLINK_ROOT} \
        xdc_PATH=${XDC_INSTALL_DIR} \
        hdvpss_PATH=${HDVPSS_INSTALL_DIR} \
        lindevkit_PATH=${STAGING_DIR_TARGET}/usr \
        rtp_PATH=${STAGING_DIR_TARGET}/usr \
        CODEGEN_PATH_M3=${CODEGEN_ARM_INSTALL_DIR} \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
        DEST_ROOT=${S}/rebuilt-binaries

    # Clean before building next target
    cd ${S}/packages && make cleanall \
        ROOTDIR=${S}/packages \
        INTERNAL_SW_ROOT=${S}/packages \
        EXTERNAL_SW_ROOT=${DVSDK_INSTALL_DIR} \
        bios_PATH=${SYSBIOS_INSTALL_DIR} \
        fc_PATH=${FC_INSTALL_DIR} \
        osal_PATH=${OSAL_INSTALL_DIR} \
        xdais_PATH=${XDAIS_INSTALL_DIR} \
        linuxutils_PATH=${LINUXUTILS_INSTALL_DIR} \
        ce_PATH=${CE_INSTALL_DIR} \
        h264enc_PATH=${CODEC_INSTALL_DIR} \
        h264dec_PATH=${CODEC_INSTALL_DIR} \
        hdvicp20api_PATH=${HDVICP20_INSTALL_DIR} \
        ipc_PATH=${IPC_INSTALL_DIR} \
        syslink_PATH=${SYSLINK_ROOT} \
        xdc_PATH=${XDC_INSTALL_DIR} \
        hdvpss_PATH=${HDVPSS_INSTALL_DIR} \
        CODEGEN_PATH_M3=${CODEGEN_ARM_INSTALL_DIR} \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
        DEST_ROOT=${S}/rebuilt-binaries

    # Build VS2 {Video Surveillance}
    cd ${S}/packages && make ${VSTARGET}  \
        ROOTDIR=${S}/packages \
        INTERNAL_SW_ROOT=${S}/packages \
        EXTERNAL_SW_ROOT=${DVSDK_INSTALL_DIR} \
        bios_PATH=${SYSBIOS_INSTALL_DIR} \
        fc_PATH=${FC_INSTALL_DIR} \
        osal_PATH=${OSAL_INSTALL_DIR} \
        xdais_PATH=${XDAIS_INSTALL_DIR} \
        linuxutils_PATH=${LINUXUTILS_INSTALL_DIR} \
        ce_PATH=${CE_INSTALL_DIR} \
        h264enc_PATH=${CODEC_INSTALL_DIR} \
        h264dec_PATH=${CODEC_INSTALL_DIR} \
        hdvicp20api_PATH=${HDVICP20_INSTALL_DIR} \
        ipc_PATH=${IPC_INSTALL_DIR} \
        syslink_PATH=${SYSLINK_ROOT} \
        xdc_PATH=${XDC_INSTALL_DIR} \
        hdvpss_PATH=${HDVPSS_INSTALL_DIR} \
        CODEGEN_PATH_M3=${CODEGEN_ARM_INSTALL_DIR} \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
        DEST_ROOT=${S}/rebuilt-binaries

}

do_install() {
    install -d ${D}${OMX_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${OMX_INSTALL_DIR_RECIPE}
}

