DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

inherit pkgconfig

PR = "r1"
PV = "05_02_00_03"

COMPATIBLE_MACHINE = "ti816x"

S = "${WORKDIR}/omx-ti816x_${PV}"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/OMX/${PV}/exports/omx-ti816x_${PV}.tar.gz;name=omx"

SRC_URI[omx.md5sum] = "6d2d3603f834deb66ea262a68f85f18f"
SRC_URI[omx.sha256sum] = "3217e391835fd30ad3cef07c3003dd08888f2e3dcbb9af6503de0799bbf5e84e"

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

    oe_runmake CORE=a8host libs 
    oe_runmake CORE=a8host EXAMPLES_ROOT=${S}/packages DEST_ROOT=${S}/bin examples
}

do_install() {
    install -d ${D}${OMX_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${OMX_INSTALL_DIR_RECIPE}
}
