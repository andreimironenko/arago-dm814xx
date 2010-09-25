DESCRIPTION = "C6000 Interprocessor communication (IPC) Software"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ipc/index.html"

SECTION = "devel"
LICENSE = "TI" 

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc

PR = "r1"

S = "${WORKDIR}/ipc_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ipc/${PV}/exports/ipc_setuplinux_${PV}.bin;name=ipcbin"

DEPENDS = "ti-sysbios ti-cgt6x ti-xdctools"

BINFILE="ipc_setuplinux_${PV}.bin"
TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"

do_install() {
    install -d ${D}${IPC_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${IPC_INSTALL_DIR_RECIPE}
}
