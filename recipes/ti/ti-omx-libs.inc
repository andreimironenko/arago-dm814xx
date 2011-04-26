DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

inherit pkgconfig

PR = "r1"

S = "${WORKDIR}/omx_${PV}"

SRC_URI = "http://install.source.dir.local/omx_${PV}.tar.gz;name=omx"

SRC_URI =+ "file://omx.pc"

SYSLINK_ROOT = "${WORKDIR}/syslink_${SYSLINKPV}${SYSLINKPVextra}"
export SYSLINK_ROOT

DEPENDS = "ti-sysbios ti-xdctools ti-ipc ti-cgt470 ti-osal ti-syslink"
DEPENDS += "ti-ivahd-hdvicp20api ti-codecs-dm816x"
DEPENDS += "ti-xdais ti-codec-engine ti-framework-components"
DEPENDS += "ti-hdvpss ti-uia"

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
        uia_PATH=${UIA_INSTALL_DIR} \
        lindevkit_PATH=${STAGING_DIR_TARGET}/usr \
        rtp_PATH=${STAGING_DIR_TARGET}/usr \
        CODEGEN_PATH_M3=${CODEGEN_ARM_INSTALL_DIR} \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
"

PARALLEL_MAKE = ""

do_configure() {
    sed -i -e s:@VERSION@:${PV}:g ${WORKDIR}/omx.pc
    
    cp ${WORKDIR}/omx.pc ${S}
}

do_compile() {
    # Build OMX Library Package
    cd ${S}/packages

    # oe_runmake CORE=a8host domx omxcore timmosal 
    oe_runmake CORE=a8host DEST_ROOT=${S}/lib domx omxcore memcfg

    oe_runmake DEST_ROOT=${S}/firmware \
               PROFILE_m3video=debug PROFILE_m3vpss=debug \
               dm816xbmducati
}

do_install() {
    install -d ${D}/omx_${PV}

    # Run the script that copies only required directories and files
    ${S}/packages/ti/omx/scripts/install_script.sh  \
          ${S}/packages/ti/omx/scripts/dirlist.txt  \
          ${S}/packages/ti/omx/scripts/filelist.txt \
          ${S} ${D}/omx_${PV}

    install -d ${D}/omx_${PV}/lib
    cp ${S}/lib/ti/omx/domx/lib/a8host/debug/domx.av5T ${D}/omx_${PV}/lib
    cp ${S}/lib/ti/omx/omxcore/lib/a8host/debug/omxcore.av5T ${D}/omx_${PV}/lib

    install -d ${D}/omx_${PV}/firmware
    cp ${S}/firmware/dm816xbm/bin/ti816x-evm/*.xem3 ${D}/omx_${PV}/firmware

    cp -r ${S}/packages/ti/omx/docs ${D}/omx_${PV}

    install -d ${D}/omx_${PV}/include
    cd ${D}/omx_${PV}/include
    ln -s ../packages/ti/omx/interfaces/openMaxv11;
    for i in vdec venc vfpc vfcc vfdc; do
        ln -s ../packages/ti/omx/comp/$i/omx_$i.h;
    done

    install -d ${D}/${libdir}/pkgconfig
    install ${S}/omx.pc ${D}/${libdir}/pkgconfig/

    install -d ${D}${OMX_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${OMX_INSTALL_DIR_RECIPE}
}

PACKAGES += "ti-omx-libs-sourcetree"
FILES_ti-omx-libs-sourcetree = "omx_${PV}"

FILES_ti-omx-libs-dev = "${libdir}/*"
