DESCRIPTION = "TI Remote Processor Execute"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

PR = "r3"

PVExtra = ""
S = "${WORKDIR}/rpe-${PV}${PVExtra}"
COMPATIBLE_MACHINE = "(ti816x|ti814x|ti811x)"
SRC_URI = "http://software-dl.ti.com:82/dsps/dsps_public_sw/rpe/${PV}/exports/rpe-${PV}.tar.gz;name=rpe \
           file://aacdectest_c6a811x.cfg \
           file://aacenctest_c6a811x.cfg \
           file://mp3dectest_c6a811x.cfg \
"

DEPENDS  = "ti-xdais alsa-lib ti-uia ti-syslink ti-xdctools ti-sysbios ti-ipc ti-linuxutils"
DEPENDS += "ti-c674x-aaclcdec ti-media-controller-utils"

PLATFORM_ti816x-evm = "ti816x"
PLATFORM_ti814x-evm = "ti814x"
PLATFORM_ti811x-evm = "ti811x"
PLATFORM_c6a811x-evm = "c6a811x"

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
        RPE_USEAACDEC=1 \
        RPE_USEAACENC=0 \
        RPE_USEMP3DEC=0 \
"

PARALLEL_MAKE = ""

do_install() {
    oe_runmake DESTDIR=${D} install
}

do_copyimage() {

    # Copy only for c6a811x platfrom
    if [[ ${PLATFORM} = c6a811x ]]
    then
      cp -f ${WORKDIR}/aacdectest_c6a811x.cfg ${S}/examples/aacdec/aacdectest.cfg
      cp -f ${WORKDIR}/aacenctest_c6a811x.cfg ${S}/examples/aacenc/aacenctest.cfg
      cp -f ${WORKDIR}/mp3dectest_c6a811x.cfg ${S}/examples/mp3dec/mp3dectest.cfg
    fi
}
addtask copyimage after do_unpack before do_patch

PACKAGES += "ti-rpe-examples"
FILES_ti-rpe-examples += "${installdir}/*"
INSANE_SKIP_${PN} = True
INSANE_SKIP_ti-rpe-examples = True
