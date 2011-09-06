DESCRIPTION = "TI DSP Benchmark applications"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c6accel/latest/index_FDS.html"

SECTION = "devel"
LICENSE = "TI Proprietary"

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc

PLATFORM_omapl138 = "omapl138"
PLATFORM_ti816x = "TI816X"
PLATFORM_ti814x = "TI814X"
PLATFORM ?= "<UNDEFINED_PLATFORM>"

COMPATIBLE_MACHINE = "(da850-omapl138-evm|ti816x|ti814x)"

PROVIDES += "ti-dsp-benchmark-apps"

PR = "r1"

S = "${WORKDIR}/dsp_benchmark_demo_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c6accel/latest/exports//dsp_benchmark_demo_${PV}_Linux-x86_Setup.bin;name=dspbenchmarksbin"

BINFILE="dsp_benchmark_demo_${PV}_Linux-x86_Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir"

DEPENDS = "ti-codec-engine ti-xdais"
DEPENDS += "ti-edma3lld ti-cgt6x ti-xdctools ti-framework-components"
DEPENDS += "ti-c6accel"
DEPENDS_omapl138 += "ti-dsplink ti-dspbios ti-biosutils"
DEPENDS_ti816x += "ti-sysbios ti-syslink ti-c6accel"
DEPENDS_ti814x += "ti-sysbios ti-syslink ti-c6accel"

LINK_omapl138 = "${LINK_INSTALL_DIR}"
LINK_ti814x = "${SYSLINK_INSTALL_DIR}"
LINK_ti816x = "${SYSLINK_INSTALL_DIR}"
LINK ?= "<UNDEFINED LINK>"

BIOS_omapl138 = "${BIOS_INSTALL_DIR}"
BIOS_ti814x = "${SYSBIOS_INSTALL_DIR}"
BIOS_ti816x = "${SYSBIOS_INSTALL_DIR}"
BIOS ?= "<UNDEFINED BIOS>"

do_compile() {
    unset VERBOSE
    cd ${S}
    make \
    PLATFORM="${PLATFORM}" \
    DEMO_INSTALL_DIR="${S}" \
    CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
    XDAIS_INSTALL_DIR="${XDAIS_INSTALL_DIR}" \
    LINK_INSTALL_DIR=${LINK} \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    EDMA3_LLD_INSTALL_DIR="${EDMA3_LLD_INSTALL_DIR}" \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
    BIOS_INSTALL_DIR=${BIOS} \
    BIOSUTILS_INSTALL_DIR="${BIOSUTILS_INSTALL_DIR}" \
    SERVER_INSTALL_DIR="${CODEC_INSTALL_DIR}" \
    C6ACCEL_INSTALL_DIR="${C6ACCEL_INSTALL_DIR}" \
    CSTOOL_DIR="${TOOLCHAIN_PATH}" \
    CSTOOL_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
    all

}

do_install() {
    install -d ${D}${DSP_BENCHMARK_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${DSP_BENCHMARK_INSTALL_DIR_RECIPE}

    cd ${S}
    make \
      PLATFORM="${PLATFORM}" \
      DEMO_INSTALL_DIR="${S}" \
      EXEC_DIR_DEMO="${D}/${installdir}/dsp-benchmark-apps" \
      CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
      LINK_INSTALL_DIR=${LINK} \
      C6ACCEL_INSTALL_DIR="${C6ACCEL_INSTALL_DIR}" \
      install
}

PACKAGES += "ti-dsp-benchmark-apps"
FILES_ti-dsp-benchmark-apps = "${installdir}/dsp-benchmark-apps/*"
INSANE_SKIP_ti-dsp-benchmark-apps = True
RDEPENDS_ti-dsp-benchmark-apps += "ti-cmem-module"
RDEPENDS_ti-dsp-benchmark-apps_omapl138 += "ti-dsplink-module "
RDEPENDS_ti-dsp-benchmark-apps_ti814x += "ti-syslink-module "
RDEPENDS_ti-dsp-benchmark-apps_ti816x += "ti-syslink-module "

