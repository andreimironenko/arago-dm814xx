DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

inherit pkgconfig

PR = "r1"
PV = "05_02_00_04"

COMPATIBLE_MACHINE = "ti814x"

S = "${WORKDIR}/omx-ti814x_${PV}"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/OMX/${PV}/exports/omx-ti814x_${PV}.tar.gz;name=omx \
           file://0001-ti-omx-Remove-c6xtest-support-from-ti814x.patch \
"

SRC_URI[omx.md5sum] = "94fd6309d143305b3cb460ab0e5543ff"
SRC_URI[omx.sha256sum] = "b0743f8ba1d97dd11ce6e25cc346cc3edfff01fe0fef8f413b05161cdfbcc0f1"

DEPENDS = "ti-sysbios ti-xdctools ti-ipc ti-cgt470 ti-osal ti-syslink"
DEPENDS += "ti-ivahd-hdvicp20api ti-codecs-dm816x"
DEPENDS += "ti-xdais ti-codec-engine ti-framework-components"
DEPENDS += "ti-hdvpss ti-uia ti-slog"

EXTRA_OEMAKE = "ROOTDIR=${S}/packages \
        INTERNAL_SW_ROOT=${S}/packages \
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
        hdvicp20api_PATH=${HDVICP20_INSTALL_DIR} \
        ipc_PATH=${IPC_INSTALL_DIR} \
        syslink_PATH=${SYSLINK_INSTALL_DIR} \
        xdc_PATH=${XDC_INSTALL_DIR} \
        hdvpss_PATH=${HDVPSS_INSTALL_DIR} \
        uia_PATH=${UIA_INSTALL_DIR} \
        slog_PATH=${SLOG_INSTALL_DIR} \
        lindevkit_PATH=${STAGING_DIR_TARGET}/usr \
        rtp_PATH=${STAGING_DIR_TARGET}/usr \
        CODEGEN_PATH_M3=${CODEGEN_ARM_INSTALL_DIR} \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
"

PARALLEL_MAKE = ""

do_compile() {
    # Build OMX Library Package
    cd ${S}/packages

    oe_runmake CORE=a8host PLATFORM=ti814x-evm libs 
    oe_runmake CORE=a8host PLATFORM=ti814x-evm EXAMPLES_ROOT=${S}/packages DEST_ROOT=${S}/bin examples
}

do_install() {
    install -d ${D}${OMX_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${OMX_INSTALL_DIR_RECIPE}
}
