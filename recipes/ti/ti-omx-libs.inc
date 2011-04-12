DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/omx_${PV}"

SRC_URI = "http://install.source.dir.local/omx_${PV}.tar.gz;name=omx"

SYSLINK_ROOT = "${WORKDIR}/syslink_${SYSLINKPV}${SYSLINKPVextra}"
export SYSLINK_ROOT

DEPENDS = "ti-sysbios ti-xdctools ti-ipc ti-cgt470 ti-osal ti-syslink"
DEPENDS += "ti-ivahd-hdvicp20api ti-codecs-dm816x"
DEPENDS += "ti-xdais ti-codec-engine ti-framework-components"
DEPENDS += "ti-hdvpss"

PROVIDES = "ti-omx-libs-sourcetree"

EXTRA_OEMAKE = "ROOTDIR=${S}/packages \
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
"

PARALLEL_MAKE = "-j 2"

do_compile() {
    # Build OMX Library Package
    cd ${S}/packages

    # oe_runmake CORE=a8host domx omxcore timmosal 
    oe_runmake CORE=a8host DEST_ROOT=${S}/lib domx omxcore

    oe_runmake DEST_ROOT=${S}/firmware \
               PROFILE_m3video=debug PROFILE_m3vpss=debug \
               CONFIG_BLD_XDC_m3=${S}/packages/ti/omx/demos/ti816x/davinciMC/config.bld \
               vdec_sntducati
}

do_install() {
    install -d ${D}/omx_${PV}
    cp -pPrf ${S}/packages ${D}/omx_${PV}

    cp -r ${S}/lib ${D}/omx_${PV}
    cp -r ${S}/firmware ${D}/omx_${PV}
    cp -r ${S}/packages/ti/omx/docs ${D}/omx_${PV}

    install -d ${D}/omx_${PV}/include
    cd ${D}/omx_${PV}/include
    ln -s ../packages/ti/omx/interfaces/openMaxv11;
    for i in vdec venc vfpc vfcc vfdc; do
        ln -s ../packages/ti/omx/comp/$i/omx_$i.h;
    done
}

PACKAGES += "ti-omx-libs-sourcetree"
FILES_ti-omx-libs-sourcetree = "omx_${PV}"
