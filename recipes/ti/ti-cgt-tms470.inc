DESCRIPTION = "TI DSP Code Generation Tools"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
SECTION = "devel"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc

PR = "r1"

S = "${WORKDIR}/cgt-tms470_${PV}"

SRC_URI = "http://install.source.dir.local/ti_cgt_tms470_${PVwithdots}_setup_linux_x86.bin;name=cgttms470bin"

BINFILE="ti_cgt_tms470_${PVwithdots}_setup_linux_x86.bin"
TI_BIN_UNPK_CMDS="Y:qY:workdir: : "
TI_BIN_UNPK_WDEXT="/cgt-tms470_${PV}"

do_install() {
    install -d ${D}${TMS470_CODEGEN_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${TMS470_CODEGEN_INSTALL_DIR_RECIPE}
}

