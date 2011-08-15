DESCRIPTION = "EZSDK Integra Signal Analyzer Demo application for C6A816x processor"
SECTION = "devel"
LICENSE = "BSD"
 
require ti-paths.inc
# require ti-staging.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r3"

S  = "${WORKDIR}/signal-analyzer-demo_${PV}/"
SADEMO_ROOT  = "${WORKDIR}/signal-analyzer-demo_${PV}/app"

SRC_URI = "http://install.source.dir.local/signal-analyzer-demo_${PV}.tar.gz;name=sademo"

DEPENDS = "qwt ti-c6accel"
PROVIDES += "ti-integra-demos"

PLATFORM ?= "<UNDEFINED>"
PLATFORM_ti816x = "TI816X"
PLATFORM_ti814x = "TI814X"

PLATFORMXDC ?= "<UNDEFINED>"
PLATFORMXDC_ti816x = "ti.platforms.evmDM8168"
PLATFORMXDC_ti814x = "ti.platforms.evmTI814X"

UNITSERVERDIR ?= "<UNDEFINED>"
UNITSERVERDIR_ti816x = "TI816X"
UNITSERVERDIR_ti814x = "ti814x"

PACKAGE_STRIP = "no"

inherit qt4e

do_prepsources () {
    cd ${S}
    sed -i \
        -e s:^C6ACCEL_ROOT.*:C6ACCEL_ROOT=${C6ACCEL_INSTALL_DIR}:g \
        ${S}/sigan-gui-r.pro
}

addtask prepsources after do_unpack before do_configure 

do_compile() {
    cd ${SADEMO_ROOT} && make CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
        XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
        BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR}" \
        OSAL_INSTALL_DIR="${OSAL_INSTALL_DIR}" \
        IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
        SYSLINK_INSTALL_DIR="${SYSLINK_INSTALL_DIR}" \
        XDAIS_INSTALL_DIR="${XDAIS_INSTALL_DIR}" \
        FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
        CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
        C6ACCEL_INSTALL_DIR="${C6ACCEL_INSTALL_DIR}" \
        CGTOOLS_V5T="${TOOLCHAIN_PATH}" \
        CGTOOLS_C64P="${CODEGEN_INSTALL_DIR}" \
        CGTOOLS_C674="${CODEGEN_INSTALL_DIR}" \
        CGTOOLS_C674ELF="${CODEGEN_INSTALL_DIR}" \
        CSTOOL_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
        PLATFORM_XDC=${PLATFORMXDC} \
        PLATFORM=${PLATFORM} \
        FFT_QT_GUI_INSTALL_DIR=${S} all

   cd ${S} && make PLATFORM=${PLATFORM} C6ACCEL_ROOT=${C6ACCEL_INSTALL_DIR}
}

do_install() {
    install -d ${D}/${installdir}/ti-integra-demos
    cp -pPrf ${S}/sigan_demo_app ${D}/${installdir}/ti-integra-demos
    cp -pPrf ${SADEMO_ROOT}/scripts/bin/runSADemo ${D}/${installdir}/ti-integra-demos
    cp -pPrf ${SADEMO_ROOT}/scripts/bin/fft_demo_8khz.pcm ${D}/${installdir}/ti-integra-demos
 
    # Hack as postinst scripts are not working
    cp -pPrf ${C6ACCEL_INSTALL_DIR}/soc/app/c6accel_dsplib_testapp/test_files ${D}/${installdir}/ti-integra-demos
	for i in $(find ${C6ACCEL_INSTALL_DIR}/soc/packages/ti/c6accel_unitservers -name "*.xe674"); do
       install $i ${D}/${installdir}/ti-integra-demos
    done
}

PACKAGES += "ti-integra-demos"
FILES_ti-integra-demos = "${installdir}/ti-integra-demos/*"
RDEPENDS_ti-integra-demos += "ti-c6accel-apps"
INSANE_SKIP_ti-integra-demos = True
