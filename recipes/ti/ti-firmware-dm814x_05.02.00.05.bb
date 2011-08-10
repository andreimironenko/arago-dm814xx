DESCRIPTION = "TI Firmware for TI816x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PV="05_02_00_05"

COMPATIBLE_MACHINE = "ti814x"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "76376c935ab6eddf54007d7f5f53be19"
SRC_URI[firmware.sha256sum] = "d4ae2fe2bb7aee49b16491e25f9f28afe309dddddc56e18c500e422b4e357be1"

S = "${WORKDIR}/firmware-ti814x_${PV}"

do_install() {
    install -d ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xem3 ${D}/${installdir}/ti-media-controller-utils
}

PACKAGES += "ti-firmware"
FILES_ti-firmware = "${installdir}/ti-media-controller-utils"
