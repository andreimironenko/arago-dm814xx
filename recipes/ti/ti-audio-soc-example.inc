DESCRIPTION = "TI Audio Soc devices example"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/apps_processors/Audio_soc_example/1_00/index_FDS.html"

SECTION = "devel"
LICENSE = "TI BSD"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "(da830-omapl137-evm|da850-omapl138-evm)"

PR = "r1"

S = "${WORKDIR}/audio_soc_example_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/apps_processors/Audio_soc_example/1_00/exports/audio_soc_example_${PV}.tar.gz;name=audiosoctarball"

DEPENDS = "ti-dsplink ti-dspbios ti-edma3lld"
DEPENDS += "ti-cgt6x virtual/kernel ti-xdctools"

PLATFORM_da830-omapl137-evm = "omapl137" 
PLATFORM_da850-omapl138-evm = "omapl138" 
 
do_configure() {
    cd ${S}/gpp

    # Fix Makefile to find DSPLink pathes
    sed -i Makefile -e s:/\packages::g
}

do_compile() {
    # Build gpp sample application
    cd ${S}
    make \
    PLATFORM="${PLATFORM}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOLS_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    LINUXKERNEL_INSTALL_DIR="{STAGING_KERNEL_DIR}" \
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    BIOSPSP_INSTALL_DIR="TEST" \
    EDMA3LLD_INSTALL_DIR="${EDMA3_LLD_INSTALL_DIR}" \
    gpp

}


do_install() {
    install -d ${D}${AUDIO_SOC_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${AUDIO_SOC_INSTALL_DIR_RECIPE}
}

