DESCRIPTION = "C6000 SYSLINK  Software"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/index.html"

SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc
require ti-staging.inc

PROVIDES = "ti-syslink-module"
PROVIDES += "ti-syslink-samples"

# This package builds a kernel module, use kernel PR as base and append a local version
PR = "${MACHINE_KERNEL_PR}"
PR_append = "h"

S = "${WORKDIR}/syslink_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/${PV}/exports/syslink_${PV}.tar.gz;name=syslinktarball"

DEPENDS = "ti-sysbios ti-cgt6x ti-cgt-tms470 ti-xdctools ti-ipc"
DEPENDS += "virtual/kernel"

SYSLINKPLATFORM_c6a816x-evm          = "DM8168"
SYSLINKPLATFORM          ?= "<UNDEFINED_SYSLINKPLATFORM>"

SYSLINKXDCTARGETS_c6a816x-evm          = "ti.targets.arm.elf.M3 ti.targets.elf.C674"
SYSLINKXDCTARGETS          ?= "<UNDEFINED_SYSLINKXDCTARGETS>"

SYSLINKHLOSSAMPLES = "frameq gateMP heapBufMP heapMemMP listMP messageQ notify procMgr ringIO ringIO_gpp sharedRegion"

SYSLINKRTOSSAMPLES = "notify messageQ frameq heapBufMP heapBufMP listMP ringIO ringIO_gpp "
SYSLINKRTOSSAMPLES += "sharedRegion gateMP ringIO_dm8168 singlecore/frameq singlecore/ringio"

KERNEL_VERSION = "${@base_read_file('${STAGING_KERNEL_DIR}/kernel-abiversion')}"

# Exported Variables needed by build
SYSLINK_ROOT = "${S}"
export SYSLINK_ROOT

XDCPATH = "${IPC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages" 
export XDCPATH

XDCTARGETS="${SYSLINKXDCTARGETS}" 
export XDCTARGETS

XDCBUILDCFG="${SYSLINK_ROOT}/config.bld"
export XDCBUILDCFG

do_configure() {
    # Update config.bld for CGTools
    # TODO: The regex of removing targets is DM1868 specific and needs to be cleaned up.
    sed -i \
        -e s:^C64.rootDir.*:C64.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^C64P_COFF.rootDir.*:C64P_COFF.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^C64P_ELF.rootDir.*:C64P_ELF.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^C67P_ELF.rootDir.*:C67P_ELF.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^C674_ELF.rootDir.*:C674_ELF.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^C674_COFF.rootDir.*:C674_COFF.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^Arm9.rootDir.*:Arm9.rootDir\ =\ \"${TMS470_CODEGEN_INSTALL_DIR}\":g \
        -e s:^M3_ELF.rootDir.*:M3_ELF.rootDir\ =\ \"${TMS470_CODEGEN_INSTALL_DIR}\":g \
        -e s:C64P_COFF,://C64P_COFF:g \
        -e s:C64P_ELF,://C64P_ELF:g \
        ${S}/config.bld

}

do_compile() {
    # Build the Kernel Module
    # Followed instructions from DSP/LINK recipe to unset exported variables
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    cd ${SYSLINK_ROOT}/ti/syslink/utils/hlos/knl/Linux && make \
        ARCH="${TARGET_ARCH}" \
        CROSS_COMPILE="${TARGET_PREFIX}" \
        SYSLINK_PLATFORM="${SYSLINKPLATFORM}" \
        KDIR="${STAGING_KERNEL_DIR}"

    # Build the knl samples. 
    for sample in ${SYSLINKHLOSSAMPLES}; do
        cd ${SYSLINK_ROOT}/ti/syslink/samples/hlos/$sample/knl/Linux
        make ARCH="${TARGET_ARCH}" \
            CROSS_COMPILE="${TARGET_PREFIX}" \
            SYSLINK_PLATFORM="${SYSLINKPLATFORM}" \
            KDIR="${STAGING_KERNEL_DIR}"
    done

    # Build the User Library
    cd ${SYSLINK_ROOT}/ti/syslink/utils/hlos/usr/Linux && make CROSS_COMPILE="${TARGET_PREFIX}"

    # Build the User side samples
    cd ${SYSLINK_ROOT}/ti/syslink/utils/hlos/usr/Linux &&
        make ARCH="${TARGET_ARCH}" \
        CROSS_COMPILE="${TARGET_PREFIX}" \
        SYSLINK_PLATFORM="${SYSLINKPLATFORM}"

    cd ${SYSLINK_ROOT}/ti/syslink/samples/hlos/procMgr/usr/Linux &&
        make ARCH="${TARGET_ARCH}" \
        CROSS_COMPILE="${TARGET_PREFIX}" \
        SYSLINK_PLATFORM="${SYSLINKPLATFORM}"
 
    for sample in ${SYSLINKHLOSSAMPLES}; do
        cd ${SYSLINK_ROOT}/ti/syslink/samples/hlos/$sample/usr/Linux
        make ARCH="${TARGET_ARCH}" \
            CROSS_COMPILE="${TARGET_PREFIX}" \
            SYSLINK_PLATFORM="${SYSLINKPLATFORM}"
    done

    # Build the RTOS code.
    # Don't build samples yet. As a hack, we move the samples out and after the build move them back.
    mv ${SYSLINK_ROOT}/ti/syslink/samples ${S}
    cd ${SYSLINK_ROOT}/ti/syslink && \
        ${XDC_INSTALL_DIR}/xdc all -PR . 
    mv ${S}/samples ${SYSLINK_ROOT}/ti/syslink

    # Now build the RTOS samples in parallel. 
    # First build platforms.
    cd ${SYSLINK_ROOT}/ti/syslink/samples/rtos/platforms && \
        ${XDC_INSTALL_DIR}/xdc all -PR . 

    # Now build the remaining samples. Build in parallel as per local.conf. 
    cd ${SYSLINK_ROOT}/ti/syslink/samples/rtos && \
        echo ${SYSLINKRTOSSAMPLES} | xargs -n 1 -P ${BB_NUMBER_THREADS} -t ${XDC_INSTALL_DIR}/xdc all -P

}

do_install() {
    # Install the kernel module
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/syslink
    install -m 0755 ${SYSLINK_ROOT}/ti/syslink/lib/modules/${SYSLINKPLATFORM}/syslink.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/syslink/ 

    # Install the Syslink library in the filesystem 
    install -d ${D}/${installdir}/ti-syslink/lib
    install -m 0755 ${SYSLINK_ROOT}/ti/syslink/lib/syslink.a ${D}/${installdir}/ti-syslink/lib/

    # Install the Syslink samples in the filesystem 
    install -d ${D}/${installdir}/ti-syslink-samples
    cp -pPrf ${SYSLINK_ROOT}/ti/syslink/lib/samples/* ${D}/${installdir}/ti-syslink-samples

    install -d ${D}${SYSLINK_INSTALL_DIR_RECIPE}/packages
    cp -pPrf ${S}/* ${D}${SYSLINK_INSTALL_DIR_RECIPE}/packages
}

