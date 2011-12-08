require ti-firmware-dm816x.inc

PV = "05_02_00_30"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "93740b911d3929689b84d1a810622c0d"
SRC_URI[firmware.sha256sum] = "d9c4f621b6b9599d8446bd39f5bb24d2cff03691b5e10aecbb843825bc963947"
