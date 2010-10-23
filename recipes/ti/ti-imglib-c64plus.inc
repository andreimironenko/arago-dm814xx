DESCRIPTION = "TI 64x+ TI Image Library" 
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c64p_imglib/latest/index_FDS.html"
SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc
require ti-eula-unpack.inc

PR = "r1"

S = "${WORKDIR}/c64plus-imglib_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c64p_imglib/latest/exports//c64plus-imglib_${PV}_Linux-x86_Setup.bin;name=imglibbin"

BINFILE="c64plus-imglib_${PV}_Linux-x86_Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir"

do_compile() {
    :
}

do_install() {
    install -d ${D}${IMGLIB_C64P_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${IMGLIB_C64P_INSTALL_DIR_RECIPE}
}
