DESCRIPTION = "TI Firmware for TI816x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PV="5_02_00_01"

COMPATIBLE_MACHINE = "ti816x"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/05_02_00_01/exports/firmware-ti816x_05_02_00_01.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "40efc79a6bda3fcae9814465d8dbed86"
SRC_URI[firmware.sha256sum] = "dff0f4d4f4e1a9bdcf271802b496645a4e0e0491da4b8100394aa4c08b379674"

S = "${WORKDIR}/firmware-ti816x_05_02_00_01"

do_install() {
    install -d ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xem3 ${D}/${installdir}/ti-media-controller-utils
}

PACKAGES += "ti-firmware"
FILES_ti-firmware = "${installdir}/ti-media-controller-utils"
