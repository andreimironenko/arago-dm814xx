require ti-firmware-dm816x.inc

PV = "05_02_00_27"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "39f1b7393bb6d0b66f54a8737462631b"
SRC_URI[firmware.sha256sum] = "4f725aeb91355b468e4f646633a37c7a8094e2a8e913f9157b3f4007c6fc65f1"
