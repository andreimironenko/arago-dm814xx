DESCRIPTION = "SYSLINK Inter-Processor Communications (IPC) for TI ARM/DSP processors"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/index.html"
SECTION = "devel"
LICENSE = "BSD"

PVExtra = ""

require ti-paths.inc
require ti-staging.inc

PROVIDES = "ti-syslink-module"
PROVIDES += "ti-syslink-examples"

# This package builds a kernel module, use kernel PR as base and append a local version
PR = "${MACHINE_KERNEL_PR}"
PR_append = "i"
PVExtra = ""

S = "${WORKDIR}/syslink_${PV}${PVExtra}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/${PV}/exports/syslink_${PV}${PVExtra}.tar.gz;name=syslinktarball"

DEPENDS = "ti-sysbios ti-xdctools ti-cgt6x ti-ipc"
DEPENDS += "virtual/kernel" 

SYSLINKDEVICE_ti816x    = "TI816X"
SYSLINKDEVICE_ti814x    = "TI814X"
SYSLINKDEVICE_omap3     = "OMAP3530"
SYSLINKDEVICE          ?= "<UNDEFINED_SYSLINKDEVICE>"

PARALLEL_MAKE = ""

EXTRA_OEMAKE = DEVICE="${SYSLINKDEVICE}" \
         GPPOS=Linux \
         LOADER=ELF \
         SDK=EZSDK \
         IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
         BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR}" \
         XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
         LINUXKERNEL="${STAGING_KERNEL_DIR}" \
         CGT_ARM_INSTALL_DIR="${TOOLCHAIN_PATH}" \
         CGT_ARM_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
         CGT_C674_ELF_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
         USE_SYSLINK_NOTIFY=0

do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    oe_runmake syslink
    oe_runmake samples
}

do_install () {
    # Install the kernel module
    oe_runmake EXEC_DIR=${D} install_driver

    # Install Samples
    oe_runmake EXEC_DIR=${D}/usr/share install_samples

    # Install/Stage the Source Tree
    install -d ${D}${SYSLINK_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${SYSLINK_INSTALL_DIR_RECIPE}
}

KERNEL_VERSION = "${@base_read_file('${STAGING_KERNEL_DIR}/kernel-abiversion')}"

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
FILES_ti-syslink-examples = "${installdir}/syslink-examples/*"
INSANE_SKIP_ti-syslink-examples = True
