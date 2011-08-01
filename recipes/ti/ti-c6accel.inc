DESCRIPTION = "TI C6Accel - DSP Software Libraries on ARM"
HOMEPAGE = "https://gforge01.dal.design.ti.com/gf/project/dsplib4arm/"

SECTION = "devel"
LICENSE = "TI TSPA"

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc

PLATFORM_omapl138 = "omapl138"
PLATFORM_omap3 = "omap3530"
PLATFORM_ti816x = "TI816X"
PLATFORM_ti814x = "TI814X"
PLATFORM ?= "<UNDEFINED_PLATFORM>"

PLATFORM_XDC_ti816x = ti.platforms.evmTI816X
PLATFORM_XDC_ti814x = ti.platforms.evmTI814X
PLATFORM_XDC ?= "<UNDEFINED_PLATFORM_XDC>"

COMPATIBLE_MACHINE = "(da850-omapl138-evm|omap3evm|dm37x-evm|ti816x|ti814x)"

PROVIDES += "ti-c6accel-apps"

PR = "r12"

S = "${WORKDIR}/c6accel_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c6accel/latest/exports//c6accel_${PV}_Linux-x86_Setup.bin;name=c6accelbin "

BINFILE="c6accel_${PV}_Linux-x86_Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir"

DEPENDS = "ti-codec-engine ti-xdais ti-syslink ti-sysbios ti-ipc ti-osal" 
DEPENDS += "ti-edma3lld ti-cgt6x ti-xdctools ti-framework-components ti-linuxutils" 

PARALLEL_MAKE = ""

do_compile() {
    unset VERBOSE
    # Build C6Accel package
    cd ${S}
    make \
    PLATFORM="${PLATFORM}" \
    PLATFORM_XDC="${PLATFORM_XDC}" \
    ARM_ISA=armv7-a \
    DSP_ISA=674 \
    CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
    XDAIS_INSTALL_DIR="${XDAIS_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${SYSLINK_INSTALL_DIR}" \
    IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
    OSAL_INSTALL_DIR="${OSAL_INSTALL_DIR}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    EDMA3_LLD_INSTALL_DIR="${EDMA3_LLD_INSTALL_DIR}" \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR}" \
    BIOSUTILS_INSTALL_DIR="${BIOSUTILS_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    SERVER_INSTALL_DIR="${CODEC_INSTALL_DIR}" \
    C6ACCEL_INSTALL_DIR="${S}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOL_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    all

}

do_install() {
    install -d ${D}${C6ACCEL_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${C6ACCEL_INSTALL_DIR_RECIPE}

    # update linker.cmd file to point to sysroot 
    sed -i ${i} -e s=${S}=${C6ACCEL_INSTALL_DIR}=g ${D}${C6ACCEL_INSTALL_DIR_RECIPE}/soc/app/c6accel_dsplib_testapp/c6accel_dsplib_testapp_config/linker.cmd

    cd ${S} 
    make \
      PLATFORM="${PLATFORM}" \
      CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
      LINK_INSTALL_DIR="${SYSLINK_INSTALL_DIR}" \
      LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
      C6ACCEL_INSTALL_DIR="${S}" \
      EXEC_DIR_C6ACCEL="${D}/${installdir}/c6accel-apps" \
      install
}

PACKAGES += "ti-c6accel-apps"
FILES_ti-c6accel-apps = "${installdir}/c6accel-apps/*"
INSANE_SKIP_ti-c6accel-apps = True
RDEPENDS_ti-c6accel-apps += "ti-cmem-module ti-syslink-module"

