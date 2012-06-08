DESCRIPTION = "TI Remote Processor Execute"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

PR = "r2"

PVExtra = ""
S = "${WORKDIR}/rpe-${PV}${PVExtra}"

SRC_URI = "http://software-dl.ti.com:82/dsps/dsps_public_sw/rpe/${PV}/exports/rpe-${PV}.tar.gz;name=rpe \
"

DEPENDS  = "ti-xdais alsa-lib ti-uia ti-syslink ti-xdctools ti-sysbios ti-ipc ti-linuxutils"
DEPENDS += "ti-c674x-aaclcdec ti-media-controller-utils"

PLATFORM_ti816x = "ti816x-evm"
PLATFORM_ti814x = "ti814x-evm"
PLATFORM_ti811x = "ti814x-evm"

EXTRA_OEMAKE = "ROOTDIR=${S} \
        PLATFORM=${PLATFORM} \
        uia_PATH=${UIA_INSTALL_DIR} \
        linuxutils_PATH=${LINUXUTILS_INSTALL_DIR} \
        ipc_PATH=${IPC_INSTALL_DIR} \
        kernel_PATH=${STAGING_KERNEL_DIR} \
        bios_PATH=${SYSBIOS_INSTALL_DIR} \
        syslink_PATH=${SYSLINK_INSTALL_DIR} \
        xdc_PATH=${XDC_INSTALL_DIR} \
        xdais_PATH=${XDAIS_INSTALL_DIR} \
        mcutils_PATH=${MCUTILS_INSTALL_DIR} \
        aaclcdec_PATH=${CODEC_INSTALL_DIR} \
        lindevkit_PATH=${STAGING_DIR_TARGET}/usr \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
        CODEGEN_PATH_DSPELF=${CODEGEN_INSTALL_DIR} \
"

PARALLEL_MAKE = ""

do_install() {
    oe_runmake DESTDIR=${D} install
}

PACKAGES += "ti-rpe-examples"
FILES_ti-rpe-examples += "${installdir}/*"
INSANE_SKIP_${PN} = True
INSANE_SKIP_ti-rpe-examples = True
