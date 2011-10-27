DESCRIPTION = "TI Firmware for TI816x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PV="05_02_00_20"

COMPATIBLE_MACHINE = "ti816x"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "9d130d14d13ae9324e9b72825aef9b2b"
SRC_URI[firmware.sha256sum] = "5aef8abe1b3f4beacc2aa6a538763ca308214944ec0edf3efd5d48853066fbf3"

S = "${WORKDIR}/firmware-ti816x_${PV}"

do_install() {
    install -d ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xem3 ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xe674 ${D}/${installdir}/ti-media-controller-utils
}

PACKAGES += "ti-firmware"
FILES_ti-firmware = "${installdir}/ti-media-controller-utils"
INSANE_SKIP_ti-firmware = True
