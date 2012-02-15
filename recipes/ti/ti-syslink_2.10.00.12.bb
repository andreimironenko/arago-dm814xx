DESCRIPTION = "SYSLINK Inter-Processor Communications (IPC) for TI ARM/DSP processors"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/index.html"
SECTION = "devel"
LICENSE = "BSD"

PV = "2_10_00_12"
PVwithdots = "2.10.00.12"
PVExtra = ""

DEFAULT_PREFERENCE = "-1"

require ti-paths.inc
require ti-staging.inc

PROVIDES = "ti-syslink-module"
PROVIDES += "ti-syslink-examples"

# This package builds a kernel module, use kernel PR as base and append a local version
PR = "${MACHINE_KERNEL_PR}"
PR_append = "j"

S = "${WORKDIR}/syslink_${PV}${PVExtra}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/syslink/${PV}/exports/syslink_${PV}${PVExtra}.tar.gz;name=syslinktarball \
"
#           file://0001-Syslink-Update-Memory-Map-for-EZSDK-5.02.patch \
#"

SRC_URI[syslinktarball.md5sum] = "a7be3c9f52b77ad6a61077a203299fa7"
SRC_URI[syslinktarball.sha256sum] = "eaf6d2d37515184e64153181532a7981a0ebeabb58bd309dfe58e9f7e2d7c39b"

DEPENDS = "ti-sysbios ti-xdctools ti-cgt6x ti-ipc"
# disable M3 compile ti-cgt470"
DEPENDS += "virtual/kernel" 

SYSLINKHLOSSAMPLES = "procMgr frameq gateMP heapBufMP heapMemMP listMP messageQ notify ringIO ringIO_gpp sharedRegion"
#SYSLINKRTOSSAMPLES = "frameq gateMP heapBufMP heapMemMP listMP messageQ notify ringIO ringIO_gpp sharedRegion"

SYSLINKDEVICE_ti816x     = "TI816X"
SYSLINKDEVICE_ti814x     = "TI814X"
SYSLINKDEVICE_omapl138   = "OMAPL1XX" 
SYSLINKDEVICE           ?= "<UNDEFINED_SYSLINKDEVICE>"

SYSLINKSUFFIX            = "xe674"
SYSLINKLOADER            = "ELF"

do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    # Build the gpp (hlos) kernel space
    make DEVICE="${SYSLINKDEVICE}" \
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
         USE_SYSLINK_NOTIFY=0 \
         syslink

    make DEVICE="${SYSLINKDEVICE}" \
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
         USE_SYSLINK_NOTIFY=0 \
         samples
}

KERNEL_VERSION = "${@base_read_file('${STAGING_KERNEL_DIR}/kernel-abiversion')}"

do_install () {

    # Install the hlos kernel module
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
    install -m 0755 ${S}/packages/ti/syslink/bin/${SYSLINKDEVICE}/syslink.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp/

    # Install the hlos example kernel modules and apps
    install -d ${D}/${installdir}/ti-syslink-examples
    install -m 0755 ${S}/packages/ti/syslink/bin/${SYSLINKDEVICE}/samples/* ${D}/${installdir}/ti-syslink-examples/

    # Install the rtos example apps 
    install -d ${D}/${installdir}/ti-syslink-examples/dsp
    cd ${S}/packages/ti/syslink/samples/rtos
    for i in $(find . -name "*.${SYSLINKSUFFIX}" | grep -i ${SYSLINKDEVICE}); do
        install ${i} ${D}/${installdir}/ti-syslink-examples/dsp/
    done

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

FILES_ti-syslink-dev = "${libdir}/*"
