DESCRIPTION = "TI C6Accel - DSP Software Libraries on ARM"
HOMEPAGE = "https://gforge01.dal.design.ti.com/gf/project/dsplib4arm/"

SECTION = "devel"
LICENSE = "TI TSPA"

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc

PLATFORM_omapl138 = "omapl138"
PLATFORM_omap3 = "omap3530"
PLATFORM_c6a816x-evm = "DM8168"
PLATFORM ?= "<UNDEFINED_PLATFORM>"

COMPATIBLE_MACHINE = "(da850-omapl138-evm|omap3evm|dm37x-evm|c6a816x-evm)"

# PROVIDES += "ti-c6accel-apps"

PR = "r8"

S = "${WORKDIR}/c6accel_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c6accel/latest/exports//c6accel_${PV}_Linux-x86_Setup.bin;name=c6accelbin "

BINFILE="c6accel_${PV}_Linux-x86_Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir"

DEPENDS = "ti-codec-engine ti-xdais ti-syslink ti-sysbios" 
DEPENDS += "ti-edma3lld ti-cgt6x ti-xdctools ti-framework-components" 

do_compile() {
    :
}

do_install() {
    install -d ${D}${C6ACCEL_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${C6ACCEL_INSTALL_DIR_RECIPE}
}
