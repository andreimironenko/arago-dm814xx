DESCRIPTION = "Bluetooth GUI Application"
SECTION = "network"
LICENSE = "BSD"

PR = "r2"

COMPATIBLE_MACHINE = "(omap3evm|am37x-evm|am335x-evm|am180x-evm|am181x-evm)"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/735/5224/bluetooth-gui-2011-11-20_v2.tar.gz \
           file://bluetooth-icon.png \
           file://bt_demo.desktop"

require recipes/matrix/matrix-gui-paths.inc

S = "${WORKDIR}/BT_Demo"

inherit qt4e

PLATFORM_am37x-evm = "omap3evm"
PLATFORM_am180x-evm = "am1808"
PLATFORM_da850-omapl138-evm = "am1808"
PLATFORM_am335x-evm = "am335x"
PLATFORM ?= "UNKNOWN"

do_install () {
       install -d ${D}${bindir}
       install -m 755 BT_Demo ${D}/${bindir}/bluetooth-gui
       install -m 755 ${S}/scripts/BT_Init_${PLATFORM}.sh ${D}${bindir}/BT_Init.sh
       install -m 755 ${S}/scripts/BT_Exit.sh ${D}${bindir}
       install -d 2755 ${D}${MATRIX_APP_DIR}/bt_demo
       install -m 644 ${WORKDIR}/bt_demo.desktop ${D}${MATRIX_APP_DIR}/bt_demo
       install -m 644 ${WORKDIR}/bluetooth-icon.png ${D}${MATRIX_APP_DIR}/bt_demo
}

FILES_${PN} +=" \
	${bindir}/bluetooth-gui \
	${bindir}/BT_Init.sh \
	${bindir}/BT_Exit.sh \
	${MATRIX_APP_DIR}/bt_demo/bt_demo.desktop \
	${MATRIX_APP_DIR}/bt_demo/bluetooth-icon.png \
	"

SRC_URI[md5sum] = "357da8591816ff05e81f31379bde9a01"
SRC_URI[sha256sum] = "aad229f585b21bf2eb4d3dee4b123271683b357bf0625b6da1ebced881595e6d"
