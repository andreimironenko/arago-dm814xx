DESCRIPTION = "TI Firmware for TI816x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PV="05_02_00_08"

COMPATIBLE_MACHINE = "ti814x"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "2b9ef341e2f05b689eb284a8d5926fee"
SRC_URI[firmware.sha256sum] = "b5e221a98533050cba267243df07c300f1bed49e1c16ceedaab00cda90b0e696"

S = "${WORKDIR}/firmware-ti814x_${PV}"

do_install() {
    install -d ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xem3 ${D}/${installdir}/ti-media-controller-utils
}

PACKAGES += "ti-firmware"
FILES_ti-firmware = "${installdir}/ti-media-controller-utils"
