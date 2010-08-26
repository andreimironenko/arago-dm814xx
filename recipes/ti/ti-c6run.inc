DESCRIPTION = "TI C6Run - DSP Code Development for ARM/Linux Users"
HOMEPAGE = "https://gforge.ti.com/gf/project/dspeasy/"

SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

PROVIDES += "ti-c6run-programs"

PR = "r0"

S = "${WORKDIR}/C6Run_${PV}/"

DEPENDS = "ti-xdctools ti-linuxutils ti-dspbios ti-dsplink ti-cgt6x "
DEPENDS_append_omap3 = "ti-local-power-manager"

# PLATFORM Definitions
C6RUNPLATFORMS_omapl137      = "omapl137"
C6RUNPLATFORMS_omapl138      = "omapl138_64MB"
C6RUNPLATFORMS_omap3evm      = "omap3530"
C6RUNPLATFORMS_dm37x-evm     = "dm3730"
C6RUNPLATFORMS_hawkboard     = "hawkboard"
C6RUNPLATFORMS_beagleboard   = "beagleboard"
C6RUNPLATFORMS              ?= "<UNDEFINED_C6RUNPLATFORMS>"


do_configure() {
    # Run config set with correct platform
    cd ${S}
    make \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \  
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \  
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOLS_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    ${C6RUNPLATFORMS}_config

}

do_prepsources() {

    # Clean the DSP/GPP backend libraries
    cd ${S}
    make \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOLS_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    clean
}

addtask prepsources after do_configure before do_compile

do_compile() {
    # Build C6Run backend libraries (no kernel modules)
    cd ${S}
    make \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOLS_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    oe_build

    # Build example apps
    make \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOLS_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    examples 

    # Build test apps
    make \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOLS_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    tests
}

do_install() {
    # Install the host package
    install -d ${D}${C6RUN_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${C6RUN_INSTALL_DIR_RECIPE}

    # Install the target package
    install -d ${D}/${installdir}/ti-c6run-programs
    cd ${S}
    make \
    INSTALL_DIR=${D}/${installdir}/ti-c6run-programs \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOLS_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    install_programs
}

PACKAGES += "ti-c6run-programs"

FILES_ti-c6run-programs = "${installdir}/ti-c6run-programs/*"
INSANE_SKIP_ti-c6run-programs = "True"

RDEPENDS_ti-c6run-programs += "ti-cmem-module ti-dsplink-module"
