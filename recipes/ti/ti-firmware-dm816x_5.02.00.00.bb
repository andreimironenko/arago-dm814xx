DESCRIPTION = "TI Firmware for TI816x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PV="5_02_00_00"

COMPATIBLE_MACHINE = "ti816x"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/05_02_00_00/exports/dm816x_firmware_05_02_00_00.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "563655ba7ff4093f8c130fee060eca53"
SRC_URI[firmware.sha256sum] = "95af0d4b37f582bea5d42aab1bde3f3a61fba40a5bfc5e940cf46f2f75cf1c0b"

S = "${WORKDIR}/dm816x_firmware_05_02_00_00"

do_install() {
    install -d ${D}/${installdir}/ti-omx
    install ${S}/*.xem3 ${D}/${installdir}/ti-omx
}

PACKAGES += "ti-firmware"
FILES_ti-firmware = "${installdir}/ti-omx"
