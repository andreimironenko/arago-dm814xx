DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

PR = "r2"

COMPATIBLE_MACHINE = "ti816x|ti814x"

S = "${WORKDIR}/omx-ti81xx_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/omx/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
"

DEPENDS = "ti-sysbios ti-xdctools ti-ipc ti-osal ti-syslink"
DEPENDS += "ti-c674x-aaclcdec ti-xdais ti-codec-engine ti-framework-components"
DEPENDS += "ti-uia ti-slog"

EXTRA_OEMAKE = "ROOTDIR=${S} \
        INTERNAL_SW_ROOT=${S}/src \
        EXTERNAL_SW_ROOT=${DVSDK_INSTALL_DIR} \
        kernel_PATH=${STAGING_KERNEL_DIR} \
        bios_PATH=${SYSBIOS_INSTALL_DIR} \
        fc_PATH=${FC_INSTALL_DIR} \
        osal_PATH=${OSAL_INSTALL_DIR} \
        xdais_PATH=${XDAIS_INSTALL_DIR} \
        linuxutils_PATH=${LINUXUTILS_INSTALL_DIR} \
        ce_PATH=${CE_INSTALL_DIR} \
        h264enc_PATH=${CODEC_INSTALL_DIR} \
        h264dec_PATH=${CODEC_INSTALL_DIR} \
        aaclcdec_PATH=${CODEC_INSTALL_DIR} \
        hdvicp20api_PATH=${HDVICP20_INSTALL_DIR} \
        ipc_PATH=${IPC_INSTALL_DIR} \
        syslink_PATH=${SYSLINK_INSTALL_DIR} \
        xdc_PATH=${XDC_INSTALL_DIR} \
        hdvpss_PATH=${HDVPSS_INSTALL_DIR} \
        uia_PATH=${UIA_INSTALL_DIR} \
        slog_PATH=${SLOG_INSTALL_DIR} \
        lindevkit_PATH=${STAGING_DIR_TARGET}/usr \
        rtp_PATH=${STAGING_DIR_TARGET}/usr \
        CODEGEN_PATH_DSPELF=${CODEGEN_INSTALL_DIR} \
        CODEGEN_PATH_M3=${CODEGEN_ARM_INSTALL_DIR} \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
"

PARALLEL_MAKE = ""

PLATFORM_ti816x = "ti816x-evm"
PLATFORM_ti814x = "ti814x-evm"

do_compile() {
    cd ${S}/src

    oe_runmake CORE=a8host PLATFORM=${PLATFORM} libs 
    oe_runmake CORE=a8host PLATFORM=${PLATFORM} EXAMPLES_ROOT=${S}/examples DEST_ROOT=${S}/bin examples
}

do_install() {
    install -d ${D}${OMX_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${OMX_INSTALL_DIR_RECIPE}
 
    cd ${S}/bin
    install -d ${D}/${installdir}/ti-omx
    for i in `find . -name "*.xv5T"`
    do
        install $i ${D}/${installdir}/ti-omx
    done
}

FILES_${PN} = "${installdir}/ti-omx/*"
INSANE_SKIP_${PN} = True
