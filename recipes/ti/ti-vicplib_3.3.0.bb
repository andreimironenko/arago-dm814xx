DESCRIPTION = "TI Video Image Co-Processor (VICP) Signal Processing Library"
HOMEPAGE = "http://focus.ti.com/docs/toolsw/folders/print/sprc831.html"
SECTION = "multimedia"

# TODO : Add compile step to enable rebuild

PV = "3_3_0"

SRC_URI[vicplibgz.md5sum] = "62f7e9cddc71ac4a19ac98170b614d40"
SRC_URI[vicplibgz.sha256sum] = "d31aa92ed3eec5861eada4471a7e553b02329846bea1252010036ec9dddc0cc1"

PR = "r1"

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc

S = "${WORKDIR}/vicplib"

SRC_URI = "http://focus.ti.com/lit/sw/sprc847f/sprc847f.gz;name=vicplibgz"

#Later this will have dependencies when we rebuild the libraries/examples
#DEPENDS = "ti-cgt6x ti-xdctools ti-dspbios ti-codec-engine" 

PRETARFILE="sprc847f"
BINFILE="vicplib-3.3-Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir:"

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_pretar_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}

do_install() {

    install -d ${D}${VICPLIB_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${VICPLIB_INSTALL_DIR_RECIPE}
}


