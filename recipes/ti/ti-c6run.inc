DESCRIPTION = "TI C6Run - DSP Code Development for ARM/Linux Users"
HOMEPAGE = "https://gforge.ti.com/gf/project/dspeasy/"

SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

PROVIDES += "ti-c6run-apps"

PR = "r4"

SRC_URI = "svn://gforge.ti.com/svn/dspeasy/;module=tags/${PV};proto=https;user=anonymous;pswd='' "

SRCREV="head"

S = "${WORKDIR}/tags/${PV}/"

DEPENDS = "ti-xdctools ti-linuxutils ti-cgt6x "
DEPENDS_append_omapl137  = "ti-dspbios ti-dsplink"
DEPENDS_append_omapl138  = "ti-dspbios ti-dsplink"
DEPENDS_append_omap3evm  = "ti-dspbios ti-dsplink ti-local-power-manager"
DEPENDS_append_dm37x-evm = "ti-dspbios ti-dsplink ti-local-power-manager"
DEPENDS_append_hawkboard = "ti-dspbios ti-dsplink"
DEPENDS_append_ti816x    = "ti-sysbios ti-syslink"
DEPENDS_append_ti814x    = "ti-sysbios ti-syslink"

# PLATFORM Definitions
C6RUNPLATFORMS_omapl137      = "omapl137"
C6RUNPLATFORMS_omapl138      = "omapl138"
C6RUNPLATFORMS_omap3evm      = "omap3530"
C6RUNPLATFORMS_dm37x-evm     = "dm3730"
C6RUNPLATFORMS_hawkboard     = "hawkboard"
C6RUNPLATFORMS_beagleboard   = "beagleboard"
C6RUNPLATFORMS_ti816x        = "TI816x"
C4RUNPLATFORMS_ti814x        = "TI814x"
C6RUNPLATFORMS              ?= "<UNDEFINED_C6RUNPLATFORMS>"

C6RUNDSPREGIONBASEADDR_omapl137    = "0xC2000000"
C6RUNDSPREGIONBASEADDR_omapl138    = "0xC2000000"
C6RUNDSPREGIONBASEADDR_omap3evm    = "0x86300000"
C6RUNDSPREGIONBASEADDR_dm37x-evm   = "0x83700000"
C6RUNDSPREGIONBASEADDR_hawkboard   = "0xC6000000"
C6RUNDSPREGIONBASEADDR_ti816x      = "0x9C000000"
C6RUNDSPREGIONBASEADDR_ti814x      = "0x9C000000"
C6RUNDSPREGIONBASEADDR            ?= "<UNDEFINED_C6RUNDSPREGIONBASEADDR>"

C6RUNDSPREGIONCMEMSIZE_omapl137    = "0x01000000"
C6RUNDSPREGIONCMEMSIZE_omapl138    = "0x01000000"
C6RUNDSPREGIONCMEMSIZE_omap3evm    = "0x00F00000"
C6RUNDSPREGIONCMEMSIZE_dm37x-evm   = "0x02200000"
C6RUNDSPREGIONCMEMSIZE_hawkboard   = "0x01000000"
C6RUNDSPREGIONCMEMSIZE_ti816x      = "0x02000000"
C6RUNDSPREGIONCMEMSIZE_ti814x      = "0x02000000"
C6RUNDSPREGIONCMEMSIZE            ?= "<UNDEFINED_C6RUNDSPREGIONCMEMSIZE>"

C6RUNDSPREGIONCODESIZE_omapl137    = "0x01000000"
C6RUNDSPREGIONCODESIZE_omapl138    = "0x01000000"
C6RUNDSPREGIONCODESIZE_omap3evm    = "0x00E00000"
C6RUNDSPREGIONCODESIZE_dm37x-evm   = "0x02700000"
C6RUNDSPREGIONCODESIZE_hawkboard   = "0x01000000"
C6RUNDSPREGIONCODESIZE_ti816x      = "0x01000000"
C6RUNDSPREGIONCODESIZE_ti814x      = "0x01000000"
C6RUNDSPREGIONCODESIZE            ?= "<UNDEFINED_C6RUNDSPREGIONCODESIZE>"

C6RUNBIOSINSTALL_omapl137  = "${BIOS_INSTALL_DIR}"
C6RUNBIOSINSTALL_omapl138  = "${BIOS_INSTALL_DIR}"
C6RUNBIOSINSTALL_omap3evm  = "${BIOS_INSTALL_DIR}"
C6RUNBIOSINSTALL_dm37x-evm = "${BIOS_INSTALL_DIR}"
C6RUNBIOSINSTALL_hawkboard = "${BIOS_INSTALL_DIR}"
C6RUNBIOSINSTALL_ti816x    = "${SYSBIOS_INSTALL_DIR}"
C6RUNBIOSINSTALL_ti814x    = "${SYSBIOS_INSTALL_DIR}"
C6RUNBIOSINSTALL ?= "<UNDEFINED_C6RUNBIOSINSTALL>"

C6RUNLINKINSTALL_omapl137  = "${LINK_INSTALL_DIR}"
C6RUNLINKINSTALL_omapl138  = "${LINK_INSTALL_DIR}"
C6RUNLINKINSTALL_omap3evm  = "${LINK_INSTALL_DIR}"
C6RUNLINKINSTALL_dm37x-evm = "${LINK_INSTALL_DIR}"
C6RUNLINKINSTALL_hawkboard = "${LINK_INSTALL_DIR}"
C6RUNLINKINSTALL_ti816x    = "${SYSLINK_INSTALL_DIR}"
C6RUNLINKINSTALL_ti814x    = "${SYSLINK_INSTALL_DIR}"
C6RUNLINKINSTALL ?= "<UNDEFINED_C6RUNLINKINSTALL>"

do_configure() {
    # Run config with correct platform
    cd ${S}
    make \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \  
    BIOS_INSTALL_DIR="${C6RUNBIOSINSTALL}" \  
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${C6RUNLINKINSTALL}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    ARM_TOOLCHAIN_PATH="${TOOLCHAIN_PATH}" \
    ARM_TOOLCHAIN_PREFIX="${TARGET_PREFIX}" \
    DSP_REGION_BASE_ADDR="${C6RUNDSPREGIONBASEADDR}" \
    DSP_REGION_CMEM_SIZE="${C6RUNDSPREGIONCMEMSIZE}" \
    DSP_REGION_CODE_SIZE="${C6RUNDSPREGIONCODESIZE}" \
    ${C6RUNPLATFORMS}_config
}

do_prepsources() {

    find ${S} -name .svn -type d | xargs rm -rf
    # Clean the DSP/GPP backend libraries
    cd ${S}
    make \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${C6RUNBIOSINSTALL}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${C6RUNLINKINSTALL}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    ARM_TOOLCHAIN_PATH="${TOOLCHAIN_PATH}" \
    ARM_TOOLCHAIN_PREFIX="${TARGET_PREFIX}" \
    clean
}

addtask prepsources after do_configure before do_compile

do_compile() {
    # Build C6Run backend libraries (no kernel modules)
    cd ${S}
    make \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${C6RUNBIOSINSTALL}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${C6RUNLINKINSTALL}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    ARM_TOOLCHAIN_PATH="${TOOLCHAIN_PATH}" \
    ARM_TOOLCHAIN_PREFIX="${TARGET_PREFIX}" \
    oe_build

    # Build example apps and test cases
    make \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${C6RUNBIOSINSTALL}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${C6RUNLINKINSTALL}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    ARM_TOOLCHAIN_PATH="${TOOLCHAIN_PATH}" \
    ARM_TOOLCHAIN_PREFIX="${TARGET_PREFIX}" \
    C6RUN_TOOLCHAIN_PATH="${S}" \
    examples tests
}

do_install() {
    # Install the host package
    install -d ${D}${C6RUN_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${C6RUN_INSTALL_DIR_RECIPE}

    # Install the target package
    install -d ${D}/${installdir}/c6run-apps
    cd ${S}
    make \
    INSTALL_DIR=${D}/${installdir}/c6run-apps \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    BIOS_INSTALL_DIR=${C6RUNBIOSINSTALL} \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${C6RUNLINKINSTALL}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
    ARM_TOOLCHAIN_PATH="${TOOLCHAIN_PATH}" \
    ARM_TOOLCHAIN_PREFIX="${TARGET_PREFIX}" \
    install_programs
}

PACKAGES += "ti-c6run-apps"

FILES_ti-c6run-apps = "${installdir}/c6run-apps/*"
INSANE_SKIP_ti-c6run-apps = "True"

RDEPENDS_ti-c6run-apps += "ti-cmem-module "
RDEPENDS_ti-c6run-apps_append_omapl137 = "ti-dsplink-module"
RDEPENDS_ti-c6run-apps_append_omapl138 = "ti-dsplink-module"
RDEPENDS_ti-c6run-apps_append_omap3evm = "ti-dsplink-module"
RDEPENDS_ti-c6run-apps_append_dm37x-evm = "ti-dsplink-module"
RDEPENDS_ti-c6run-apps_append_hawkboard = "ti-dsplink-module"
RDEPENDS_ti-c6run-apps_append_ti816x = "ti-syslink-module"
RDEPENDS_ti-c6run-apps_append_ti814x = "ti-syslink-module"
