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
PR_append = "j"
PVExtra = ""

S = "${WORKDIR}/syslink_${PV}${PVExtra}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/${PV}/exports/syslink_${PV}${PVExtra}.tar.gz;name=syslinktarball"

DEPENDS = "ti-sysbios ti-xdctools ti-cgt6x ti-ipc"
DEPENDS += "virtual/kernel" 

SYSLINKDEVICE_ti816x    = "TI816X"
SYSLINKDEVICE_ti814x    = "TI814X"
SYSLINKDEVICE_ti811x    = "TI811X"
SYSLINKDEVICE_omap3     = "OMAP3530"
SYSLINKDEVICE_omapl138  = "OMAPL1XX"
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
    oe_runmake examples
    oe_runmake samples-hlos
}

do_install () {
    # Install the kernel module
    oe_runmake EXEC_DIR=${D} install-driver

    # Install samples
    oe_runmake EXEC_DIR=${D}/usr/share/ti/examples install-examples

    # Install/Stage the Source Tree
    install -d ${D}${SYSLINK_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${SYSLINK_INSTALL_DIR_RECIPE}

    # Install samples
    #oe_runmake EXEC_DIR=${D}/usr/share/ti/examples install-examples
}

do_install_append_dm814x-evm () {
    install -d ${D}/${installdir}/syslink-examples/TI814X/helloworld
    install ${D}/usr/share/ti/examples/ex01_helloworld/release/* ${D}/${installdir}/syslink-examples/TI814X/helloworld
    install -d ${D}/${installdir}/syslink-examples/TI814X/messageq
    install ${D}/usr/share/ti/examples/ex02_messageq/release/* ${D}/${installdir}/syslink-examples/TI814X/messageq
    install -d ${D}/${installdir}/syslink-examples/TI814X/notify
    install ${D}/usr/share/ti/examples/ex03_notify/release/* ${D}/${installdir}/syslink-examples/TI814X/notify
    install -d ${D}/${installdir}/syslink-examples/TI814X/sharedregion
    install ${D}/usr/share/ti/examples/ex04_sharedregion/release/* ${D}/${installdir}/syslink-examples/TI814X/sharedregion
    install -d ${D}/${installdir}/syslink-examples/TI814X/heapbufmp 
    install ${D}/usr/share/ti/examples/ex05_heapbufmp/release/* ${D}/${installdir}/syslink-examples/TI814X/heapbufmp
    install -d ${D}/${installdir}/syslink-examples/TI814X/listmp
    install ${D}/usr/share/ti/examples/ex06_listmp/release/* ${D}/${installdir}/syslink-examples/TI814X/listmp
    install -d ${D}/${installdir}/syslink-examples/TI814X/gatemp
    install ${D}/usr/share/ti/examples/ex07_gatemp/release/* ${D}/${installdir}/syslink-examples/TI814X/gatemp
    install -d ${D}/${installdir}/syslink-examples/TI814X/ringio
    install ${D}/usr/share/ti/examples/ex08_ringio/release/* ${D}/${installdir}/syslink-examples/TI814X/ringio
    
}

do_install_append_dm816x-evm () {
    install -d ${D}/${installdir}/syslink-examples/TI816X/helloworld
    install ${D}/usr/share/ti/examples/ex01_helloworld/release/* ${D}/${installdir}/syslink-examples/TI816X/helloworld
    install -d ${D}/${installdir}/syslink-examples/TI816X/messageq
    install ${D}/usr/share/ti/examples/ex02_messageq/release/* ${D}/${installdir}/syslink-examples/TI816X/messageq
    install -d ${D}/${installdir}/syslink-examples/TI816X/notify
    install ${D}/usr/share/ti/examples/ex03_notify/release/* ${D}/${installdir}/syslink-examples/TI816X/notify
    install -d ${D}/${installdir}/syslink-examples/TI816X/sharedregion
    install ${D}/usr/share/ti/examples/ex04_sharedregion/release/* ${D}/${installdir}/syslink-examples/TI816X/sharedregion
    install -d ${D}/${installdir}/syslink-examples/TI816X/heapbufmp
    install ${D}/usr/share/ti/examples/ex05_heapbufmp/release/* ${D}/${installdir}/syslink-examples/TI816X/heapbufmp
    install -d ${D}/${installdir}/syslink-examples/TI816X/listmp
    install ${D}/usr/share/ti/examples/ex06_listmp/release/* ${D}/${installdir}/syslink-examples/TI816X/listmp
    install -d ${D}/${installdir}/syslink-examples/TI816X/gatemp
    install ${D}/usr/share/ti/examples/ex07_gatemp/release/* ${D}/${installdir}/syslink-examples/TI816X/gatemp
    install -d ${D}/${installdir}/syslink-examples/TI816X/ringio
    install ${D}/usr/share/ti/examples/ex08_ringio/release/* ${D}/${installdir}/syslink-examples/TI816X/ringio
}

do_install_append_c6a811x-evm () {
    install -d ${D}/${installdir}/syslink-examples/TI811X/helloworld
    install ${D}/usr/share/ti/examples/ex01_helloworld/release/* ${D}/${installdir}/syslink-examples/TI811X/helloworld
    install -d ${D}/${installdir}/syslink-examples/TI811X/messageq
    install ${D}/usr/share/ti/examples/ex02_messageq/release/* ${D}/${installdir}/syslink-examples/TI811X/messageq
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
#FILES_ti-syslink-examples = "/usr/share/ti/ti-syslink-tree/*"
FILES_ti-syslink-examples = "${installdir}/syslink-examples/*"
INSANE_SKIP_ti-syslink-examples = True
