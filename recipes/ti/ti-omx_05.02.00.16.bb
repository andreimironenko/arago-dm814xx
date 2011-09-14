DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

inherit pkgconfig

PR = "r1"
PV = "05_02_00_16"

COMPATIBLE_MACHINE = "ti816x"

S = "${WORKDIR}/omx-ti816x_${PV}"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-OMX/${PV}/exports/omx-ti816x_${PV}.tar.gz;name=omx \
           file://0001-Added-sdk-make-install-file-to-omx.patch \
           file://0001-env.mk-Fix-include-path-for-kernel-headers.patch \
"

SRC_URI[omx.md5sum] = "22190c38aa742c89e3f3aa16fef22aca"
SRC_URI[omx.sha256sum] = "17e1a797f4da944fb83ba558e3dbd44fe23164eb93bf512b24ba3e5db35b5b1e"

DEPENDS = "ti-sysbios ti-xdctools ti-ipc ti-cgt470 ti-osal ti-syslink"
DEPENDS += "ti-ivahd-hdvicp20api ti-codecs-dm816x ti-c674x-aaclcdec"
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

do_compile() {
    # Build OMX Library Package
    cd ${S}/packages

    oe_runmake CORE=a8host PLATFORM=ti816x-evm libs 
    oe_runmake CORE=a8host PLATFORM=ti816x-evm EXAMPLES_ROOT=${S}/packages DEST_ROOT=${S}/bin examples
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
