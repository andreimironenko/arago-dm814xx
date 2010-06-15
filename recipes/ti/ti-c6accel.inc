DESCRIPTION = "TI C6Accel - DSP Software Libraries on ARM"
HOMEPAGE = "https://gforge01.dal.design.ti.com/gf/project/dsplib4arm/"

SECTION = "devel"
LICENSE = "TI TSPA"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "(da850-omapl138-evm)"

PR = "r1"

S = "${WORKDIR}/c6accel_${PV}"

DEPENDS = "ti-dsplink ti-dspbios ti-codec-engine ti-linuxutils ti-xdais"
DEPENDS += "ti-cgt6x ti-xdctools"

do_compile() {
}

do_install() {
    install -d ${D}${C6ACCEL_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${C6ACCEL_INSTALL_DIR_RECIPE}
}

