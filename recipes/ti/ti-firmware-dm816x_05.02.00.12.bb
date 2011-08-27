DESCRIPTION = "TI Firmware for TI816x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PV="05_02_00_12"

COMPATIBLE_MACHINE = "ti816x"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "1de5e8fc71d12fc279bed76b7a9b3d7b"
SRC_URI[firmware.sha256sum] = "7c0f8e89b9d41e2f69977f22929ef1c8820b4c94c74a4ec3ed0c72d09001d346"

S = "${WORKDIR}/firmware-ti816x_${PV}"

do_install() {
    install -d ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xem3 ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xe674 ${D}/${installdir}/ti-media-controller-utils
}

PACKAGES += "ti-firmware"
FILES_ti-firmware = "${installdir}/ti-media-controller-utils"
INSANE_SKIP_ti-firmware = True
