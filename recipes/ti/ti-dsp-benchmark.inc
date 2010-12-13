DESCRIPTION = "TI DSP Benchmark applications"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c6accel/latest/index_FDS.html"

SECTION = "devel"
LICENSE = "TI TSPA"

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc

PLATFORM_omapl138 = "omapl138"
PLATFORM ?= "<UNDEFINED_PLATFORM>"

COMPATIBLE_MACHINE = "(omapl138)"

PROVIDES += "ti-dsp-benchmark-apps"

PR = "r1"

S = "${WORKDIR}/dsp_benchmark_demo_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c6accel/latest/exports//dsp_benchmark_demo_${PV}_Linux-x86_Setup.bin;name=dspbenchmarksbin"

BINFILE="dsp_benchmark_demo_${PV}_Linux-x86_Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir"

DEPENDS = "ti-codec-engine ti-xdais ti-dsplink ti-dspbios"
DEPENDS += "ti-edma3lld ti-cgt6x ti-xdctools ti-framework-components"
DEPENDS += "ti-biosutils ti-c6accel"

do_compile() {
    unset VERBOSE
    cd ${S}
    make \
    PLATFORM="${PLATFORM}" \
    DEMO_INSTALL_DIR="${S}" \
    CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
    XDAIS_INSTALL_DIR="${XDAIS_INSTALL_DIR}" \
    LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
    CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
    EDMA3_LLD_INSTALL_DIR="${EDMA3_LLD_INSTALL_DIR}" \
    CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
    XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
    FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
    BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}" \
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
      LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
      C6ACCEL_INSTALL_DIR="${C6ACCEL_INSTALL_DIR}" \
      install
}

PACKAGES += "ti-dsp-benchmark-apps"
FILES_ti-dsp-benchmark-apps = "${installdir}/dsp-benchmark-apps/*"
INSANE_SKIP_ti-dsp-benchmark-apps = True
RDEPENDS_ti-dsp-benchmark-apps += "ti-cmem-module ti-dsplink-module "

