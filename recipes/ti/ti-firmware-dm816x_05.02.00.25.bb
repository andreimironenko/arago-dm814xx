require ti-firmware-dm816x.inc

PV = "05_02_00_25"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "f8ba88b71a4253676cf438f786294e6b"
SRC_URI[firmware.sha256sum] = "a8b158973cf70bb9e25f0d309927969c3a0a41a1d7efae8e4fa42fa085581e89"
