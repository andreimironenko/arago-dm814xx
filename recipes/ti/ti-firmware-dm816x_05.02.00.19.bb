DESCRIPTION = "TI Firmware for TI816x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PV="05_02_00_19"

COMPATIBLE_MACHINE = "ti816x"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "aa334e31398d7217fdcde7b1b9846912"
SRC_URI[firmware.sha256sum] = "739f8f0f13513308cbf89294694d7f19b7170bcd62c67dab4877d08cf3f1497d"

S = "${WORKDIR}/firmware-ti816x_${PV}"

do_install() {
    install -d ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xem3 ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xe674 ${D}/${installdir}/ti-media-controller-utils
}

PACKAGES += "ti-firmware"
FILES_ti-firmware = "${installdir}/ti-media-controller-utils"
INSANE_SKIP_ti-firmware = True
