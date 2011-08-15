DESCRIPTION = "The C67x FastRTS library is an optimized floating-point math function library for C programmers using TMS320C67x devices" 
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c67_fastmath/latest/index_FDS.html"
SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc
require ti-eula-unpack.inc
require ti-staging.inc

PR = "r2"

S = "${WORKDIR}/c67xmathlib_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c67_fastmath/latest/exports//c67xmathlib_${PV}_Linux-x86_Setup.bin;name=mathlibbin"

SRC_URI += "file://0001-Added-sdk-make-install-file-to-mathlib.patch \
"

BINFILE="c67xmathlib_${PV}_Linux-x86_Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir"

do_compile() {
    :
}

do_install() {
    install -d ${D}${FASTMATHLIB_C674P_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${FASTMATHLIB_C674P_INSTALL_DIR_RECIPE}
}

