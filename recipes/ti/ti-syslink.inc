DESCRIPTION = "SYSLINK Inter-Processor Communications (IPC) for TI ARM/DSP processors"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/index.html"
SECTION = "devel"
LICENSE = "TI"

# TODO :: multi-machine, add m3 build as well?
# TODO :: proper staging?
# TODO :: kernel module examples?
# TODO :: smarter handling of config.bld
# TODO :: review XDC parallel make - why not happening automatically?
# TODO :: smarter suffix management

require ti-paths.inc
require ti-staging.inc

PROVIDES = "ti-syslink-module"
PROVIDES += "ti-syslink-examples"

# This package builds a kernel module, use kernel PR as base and append a local version
PR = "${MACHINE_KERNEL_PR}"
PR_append = "i"

S = "${WORKDIR}/syslink_${PV}${PVextra}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/${PV}/exports/syslink_${PV}.tar.gz;name=syslinktarball"

DEPENDS = "ti-sysbios ti-xdctools ti-cgt6x ti-ipc"  
# Need to add M3 support in subsequent update
#DEPENDS_append_ti816x = " ti-cgt470"
DEPENDS += "virtual/kernel" 

SYSLINKHLOSSAMPLES = "procMgr frameq gateMP heapBufMP heapMemMP listMP messageQ notify ringIO ringIO_gpp sharedRegion"
#SYSLINKRTOSSAMPLES = "frameq gateMP heapBufMP heapMemMP listMP messageQ notify ringIO ringIO_gpp sharedRegion"

# SOC_FAMILY configuration

SYSLINKPLATFORM_omap3     = "OMAP3530"
SYSLINKPLATFORM_omapl137  = "OMAPL1XX"
SYSLINKPLATFORM_omapl138  = "OMAPL1XX"
SYSLINKPLATFORM_ti816x    = "TI81XX"
SYSLINKPLATFORM          ?= "<UNDEFINED_SYSLINKPLATFORM>"

# COFF/ELF config - omap3 can be coff/elf, omapl only coff, ti816x only elf

# this really needs to be a list... in the case of ELF/COFF (and this could use DSPSUFFIX?)
SYSLINKSUFFIX_omap3       = "x64P"
SYSLINKSUFFIX_omapl137    = "x674"
SYSLINKSUFFIX_omapl138    = "x674"
SYSLINKSUFFIX_ti816x      = "xe674"
SYSLINKSUFFIX            ?= "<UNDEFINED_SYSLINKSUFFIX>"

SYSLINKLOADER_omap3       = "COFF"
SYSLINKLOADER_omapl137    = "COFF"
SYSLINKLOADER_omapl138    = "COFF"
SYSLINKLOADER_ti816x      = "ELF"
SYSLINKLOADER            ?= "<UNDEFINED_SYSLINKLOADER>"

# Do we really need to configure this? - configured already in config.bld
XDCTARGETS_omap3          = "ti.targets.C64P"
XDCTARGETS_omapl137       = "ti.targets.C674"
XDCTARGETS_omapl138       = "ti.targets.C674"
# for ti816x, we should later also build for ti.targets.arm.elf.M3
XDCTARGETS_ti816x         = "ti.targets.elf.C674"
XDCTARGETS               ?= "<UNDEFINED_XDCTARGETS>"
export XDCTARGETS

# Exported Variable needed by build
SYSLINK_ROOT = "${S}"
export SYSLINK_ROOT

XDCPATH = "${IPC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages" 
export XDCPATH

XDCBUILDCFG="${SYSLINK_ROOT}/config.bld"
export XDCBUILDCFG

do_configure() {

    # Update config.bld with rootDir paths for CodeGen Tools
    sed -i \
        -e s:^C64P_COFF.rootDir.*:C64P_COFF.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^C64P_ELF.rootDir.*:C64P_ELF.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^C674_COFF.rootDir.*:C674_COFF.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^C674_ELF.rootDir.*:C674_ELF.rootDir\ =\ \"${CODEGEN_INSTALL_DIR}\":g \
        -e s:^M3_ELF.rootDir.*:M3_ELF.rootDir\ =\ \"${CODEGEN_ARM_INSTALL_DIR}\":g \
        ${S}/config.bld
}

do_configure_append_ti816x() {

    # Update config.bld to remove unused build targets
    # - This will leave 'only the C674_ELF target'
    sed -i \
        -e s:C64P_COFF,://C64P_COFF:g \
        -e s:C64P_ELF,://C64P_ELF:g \
        -e s:C674_COFF,://C674_COFF:g \
        ${S}/config.bld

    sed -i \
        -e 's/"ti.platforms.evmDA830:dsp",//g' \
        ${S}/config.bld
}

do_configure_append_omap3() {

    # Update config.bld to remove unused build targets
    # - This will leave 'only the C64P_COFF target'
    sed -i \
        -e s:C64P_ELF,://C64P_ELF:g \
        -e s:C674_COFF,://C674_COFF:g \
        -e s:C674_ELF,://C674_ELF:g \
        ${S}/config.bld
}

do_configure_append_omapl137() {

    # Update config.bld to remove unused build targets
    # - This will leave 'only the C674_COFF target'
    sed -i \
        -e s:C64P_COFF,://C64P_COFF:g \
        -e s:C64P_ELF,://C64P_ELF:g \
        -e s:C674_ELF,://C674_ELF:g \
        ${S}/config.bld

    sed -i \
        -e 's/"ti.syslink.samples.rtos.platforms.ti816x.dsp",//g' \
        ${S}/config.bld
}

do_configure_append_omapl138() {

    # Update config.bld to remove unused build targets
    # - This will leave 'only the C674_COFF target'
    sed -i \
        -e s:C64P_COFF,://C64P_COFF:g \
        -e s:C64P_ELF,://C64P_ELF:g \
        -e s:C674_ELF,://C674_ELF:g \
        ${S}/config.bld

    sed -i \
        -e 's/"ti.syslink.samples.rtos.platforms.ti816x.dsp",//g' \
        ${S}/config.bld
}

do_prepsources () {
	
    # Prepare the tree for rebuiling - clean and generate interfaces
    cd ${SYSLINK_ROOT}/ti/syslink    
    ${XDC_INSTALL_DIR}/xdc --jobs=${BB_NUMBER_THREADS} .make -PR .
    ${XDC_INSTALL_DIR}/xdc --jobs=${BB_NUMBER_THREADS} clean -PR .
    ${XDC_INSTALL_DIR}/xdc --jobs=${BB_NUMBER_THREADS} .interfaces -PR .
}

addtask prepsources after do_configure before do_compile

do_compile() {
    # TODO :: KERNEL_CC, should use for kernel module build?
    # TODO :: Check the 'unset's

    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    # Build the gpp (hlos) kernel space
    cd ${SYSLINK_ROOT}/ti/syslink/utils/hlos/knl/Linux && make \
        ARCH="${TARGET_ARCH}" \
        CROSS_COMPILE="${TARGET_PREFIX}" \
        SYSLINK_PLATFORM="${SYSLINKPLATFORM}" \
        SYSLINK_LOADER="${SYSLINKLOADER}" \
        SYSLINK_PKGPATH="${IPC_INSTALL_DIR}/packages" \
        KDIR="${STAGING_KERNEL_DIR}"

    # Build the gpp (hlos) kernel space samples. 
    for sample in ${SYSLINKHLOSSAMPLES}; do
        cd ${SYSLINK_ROOT}/ti/syslink/samples/hlos/$sample/knl/Linux && make \
            ARCH="${TARGET_ARCH}" \
            CROSS_COMPILE="${TARGET_PREFIX}" \
            SYSLINK_PLATFORM="${SYSLINKPLATFORM}" \
            SYSLINK_LOADER="${SYSLINKLOADER}" \
            SYSLINK_PKGPATH="${IPC_INSTALL_DIR}/packages" \
            KDIR="${STAGING_KERNEL_DIR}"
    done

    # Build the gpp (hlos) user space
    cd ${SYSLINK_ROOT}/ti/syslink/utils/hlos/usr/Linux && make \
        ARCH="${TARGET_ARCH}" \
        CROSS_COMPILE="${TARGET_PREFIX}" \
        TOOLCHAIN_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
        SYSLINK_PLATFORM="${SYSLINKPLATFORM}" \
        SYSLINK_LOADER="${SYSLINKLOADER}" \
        SYSLINK_PKGPATH="${IPC_INSTALL_DIR}/packages"

    # Build the gpp (hlos) user space samples 
    for sample in ${SYSLINKHLOSSAMPLES}; do
        cd ${SYSLINK_ROOT}/ti/syslink/samples/hlos/$sample/usr/Linux && make \
            ARCH="${TARGET_ARCH}" \
            CROSS_COMPILE="${TARGET_PREFIX}" \
            TOOLCHAIN_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
            SYSLINK_PLATFORM="${SYSLINKPLATFORM}" \
            SYSLINK_LOADER="${SYSLINKLOADER}" \
            SYSLINK_PKGPATH="${IPC_INSTALL_DIR}/packages"
    done

    # Build the dsp/arm (rtos) code (system and samples)
    cd ${SYSLINK_ROOT}/ti/syslink && \
        ${XDC_INSTALL_DIR}/xdc --jobs=${BB_NUMBER_THREADS} -PR .
}

KERNEL_VERSION = "${@base_read_file('${STAGING_KERNEL_DIR}/kernel-abiversion')}"

do_install () {

    # Install the hlos kernel module
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
    install -m 0755 ${SYSLINK_ROOT}/ti/syslink/lib/modules/${SYSLINKPLATFORM}/syslink.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/

    # TODO :: Should these be put in the kernel modules tree, and installed using modprobe - unsure since these are example modules
    # Install the hlos example kernel module apps and remove duplicate copy of syslink.ko
    install -d ${D}/${installdir}/ti-syslink-examples
    install -m 0755 ${SYSLINK_ROOT}/ti/syslink/lib/modules/${SYSLINKPLATFORM}/*.ko ${D}/${installdir}/ti-syslink-examples/
    rm ${D}/${installdir}/ti-syslink-examples/syslink.ko

    # Install the hlos example apps 
    install -d ${D}/${installdir}/ti-syslink-examples/
    cp -pPrf ${SYSLINK_ROOT}/ti/syslink/lib/samples/*.exe ${D}/${installdir}/ti-syslink-examples/

    # Install the rtos example apps 
    cd ${SYSLINK_ROOT}/ti/syslink/samples/rtos
    for i in $(find . -name "*.${SYSLINKSUFFIX}"); do
        install -d ${D}/${installdir}/ti-syslink-examples/`dirname ${i} | cut -f4 -d /`
        install ${i} ${D}/${installdir}/ti-syslink-examples/`dirname ${i} | cut -f4 -d /`
    done

    # TODO :: Fixup - library/headers installation - this shouldn't go into the examples directory....
    # Needs proper staging
    # Install the library in the filesystem
    # install -d ${D}/${installdir}/ti-syslink-examples
    # install -m 0755 ${SYSLINK_ROOT}/ti/syslink/lib/syslink.a ${D}/${installdir}/ti-syslink-examples/
    
    # Install/Stage the Source Tree
    install -d ${D}${SYSLINK_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${SYSLINK_INSTALL_DIR_RECIPE}
}

PACKAGES += "ti-syslink-module"
FILES_ti-syslink-module = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/syslink.ko" 
RDEPENDS_ti-syslink-module += "update-modules"

pkg_postinst_ti-syslink-module () {
#!/bin/sh
if [ -n "$D" ]; then
	exit 1
fi

depmod -a
update-modules || true
}

pkg_postrm_ti-syslink-module () {
#!/bin/sh
update-modules || true
}

PACKAGES += "ti-syslink-examples"
RDEPENDS_ti-syslink-examples_append = " ti-syslink-module"
FILES_ti-syslink-examples = "${installdir}/ti-syslink-examples/*"
INSANE_SKIP_ti-syslink-examples = True
