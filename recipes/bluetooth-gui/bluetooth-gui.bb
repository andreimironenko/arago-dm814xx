DESCRIPTION = "Bluetooth GUI Application"
SECTION = "network"
LICENSE = "BSD"

PR = "r0"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/726/5209/bluetooth-gui-2011-11-17_v1.tar.gz"

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
       install -m 755 ${S}/scripts/BT_Init_${PLATFORM}.sh ${D}/${bindir}/BT_Init.sh
       install -m 755 ${S}/scripts/BT_Exit.sh ${D}/${bindir}
}

FILES_${PN} +=" \
	${bindir}/bluetooth-gui \
	${bindir}/BT_Init.sh \
	${bindir}/BT_Exit.sh \
	"

SRC_URI[md5sum] = "72575939e677952093383e31aeb5707e"
SRC_URI[sha256sum] = "fc39ed629865888e0a148f4909c2ad029ab169830517a072b5eaf881c622b4b0"
