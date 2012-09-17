require ti-firmware-dm814x.inc

PV = "05_02_00_40"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "6bec55a250effa41067b797159dfe5d7"
SRC_URI[firmware.sha256sum] = "9651f299585c29d5473fbf8535cc67c684fa4d75c0f3ca12fd4718c3ef7d7f4d"

