DESCRIPTION = "TI Serial Boot and Flashing board utilites"
HOMEPAGE = "http://sourceforge.net/projects/dvflashutils/"
SECTION = "dsp"
LICENSE = "TI"

PV = "2_25"
PV_dot = "2.25"


SRC_URI = "http://downloads.sourceforge.net/project/dvflashutils/OMAP-L138/v${PV_dot}/OMAP-L138_FlashAndBootUtils_${PV}.tar.gz;name=boardutilitiestarball"

SRC_URI[boardutilitiestarball.md5sum] = "f265e3b33c70b485dc5f477f266ad945"
SRC_URI[boardutilitiestarball.sha256sum] = "f5198bf730ed4e9f4604f7a606b80b4286af5d8f9b67fa3897de5e89dccc6955"

PR = "r2"

require ti-paths.inc

SRC_URI = "http://downloads.sourceforge.net/project/dvflashutils/OMAP-L138/v${PV_dot}/OMAP-L138_FlashAndBootUtils_${PV}.tar.gz;name=boardutilitiestarball"

S = "${WORKDIR}/OMAP-L138_FlashAndBootUtils_${PV}"

COMPATIBLE_MACHINE = "(da850-omapl138-evm)"

do_configure() {
    # Correct makefile to build for OMAPL138 only 
    sed -i "s:common:Common:g" ${S}/OMAP-L138/GNU/ubl/build/makefile
    sed -i "s:DSP_DEVICETYPES:#DSP_DEVICETYPES:g" ${S}/OMAP-L138/device.mak
}

do_compile () {
	cd ${S}
}

do_install () {
	install -d ${D}/${installdir}/ti-psp-tree/board-utilities
        cp -pPrf ${S}/* ${D}/${installdir}/ti-psp-tree/board-utilities
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
PACKAGE_STRIP = "no"
INSANE_SKIP_${PN} = True

