DESCRIPTION = "TI Firmware for TI811x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "ti811x"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/C6A811x-Firmware/${PV}/exports/firmware-ti811x_${PV}.tar.gz;name=firmware \
"

S = "${WORKDIR}/firmware-ti811x_${PV}"

do_install() {
    install -d ${D}/${installdir}/ti-media-controller-utils
    install ${S}/*.xem3 ${D}/${installdir}/ti-media-controller-utils
}

PACKAGES += "ti-firmware"
FILES_ti-firmware = "${installdir}/ti-media-controller-utils"
INSANE_SKIP_ti-firmware = True
