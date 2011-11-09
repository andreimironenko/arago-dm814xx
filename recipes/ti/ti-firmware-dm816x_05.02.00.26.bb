require ti-firmware-dm816x.inc

PV = "05_02_00_26"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "f0138d5b9d000529bfaf5f983032636c"
SRC_URI[firmware.sha256sum] = "3e0bb49a945210f602bb191d27281aed81964cb6c1409a5fbe29f667e575bb93"
