DESCRIPTION = "TI C674x DSPLIB  Digital Signal Processing Library" 
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c674_dsplib/latest/index_FDS.html"
SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc
require ti-eula-unpack.inc
require ti-staging.inc

PR = "r2"

S = "${WORKDIR}/c674x-dsplib_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c674_dsplib/latest/exports//c674x-dsplib_${PV}_Linux-x86_Setup.bin;name=dsplibbin"

SRC_URI += "file://0001-Added-sdk-make-install-file-to-dsplib.patch \
"

BINFILE="c674x-dsplib_1_03_00_00_Linux-x86_Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir"

do_compile() {
    :
}

do_install() {
    install -d ${D}${DSPLIB_C674_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${DSPLIB_C674_INSTALL_DIR_RECIPE}
}

INSANE_SKIP_${PN} = True
