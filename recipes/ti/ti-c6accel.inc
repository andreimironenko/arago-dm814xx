DESCRIPTION = "TI C6Accel - DSP Software Libraries on ARM"
HOMEPAGE = "https://gforge01.dal.design.ti.com/gf/project/dsplib4arm/"

SECTION = "devel"
LICENSE = "TI TSPA"

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc


COMPATIBLE_MACHINE = "(da850-omapl138-evm)"

PR = "r2"

S = "${WORKDIR}/Texas\ Instruments/c6accel_${PV}/c6accel_${PV}"

SRC_URI = "https://gforge01.dal.design.ti.com/gf/download/frsrelease/397/3619/C6Accel_${PV}_Linux-x86_Setup.bin;name=c6accelbin"


BINFILE="C6Accel_${PV}_Linux-x86_Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir"

DEPENDS = "ti-dsplink ti-dspbios ti-codec-engine ti-linuxutils ti-xdais"
DEPENDS += "ti-cgt6x ti-xdctools"

do_compile() {
}

do_install() {
    install -d ${D}${C6ACCEL_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${C6ACCEL_INSTALL_DIR_RECIPE}
}

