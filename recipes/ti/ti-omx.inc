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

DEPENDS = "ti-sysbios ti-xdctools ti-ipc ti-cgt470 ti-osal ti-syslink"
DEPENDS += "ti-ivahd-hdvicp20api ti-codecs-dm816x"
DEPENDS += "ti-xdais ti-codec-engine ti-framework-components"
DEPENDS += "ti-hdvpss"
DEPENDS += "ortp alsa-lib"

do_compile() {
    # Build VC3 (Video Conference)
    cd ${S}/packages && make vc3 \
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
        DEST_ROOT=${S}/rebuilt-binaries \
        PROFILE_m3video=debug \
        PROFILE_m3vpss=debug 

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
        DEST_ROOT=${S}/rebuilt-binaries \
        PROFILE_m3video=debug \
        PROFILE_m3vpss=debug 

    # Build VS2 {Video Surveillance}
    cd ${S}/packages && make vs2 \
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
        DEST_ROOT=${S}/rebuilt-binaries \
        PROFILE_m3video=debug \
        PROFILE_m3vpss=debug 

}

do_install() {
    install -d ${D}${OMX_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${OMX_INSTALL_DIR_RECIPE}
}

