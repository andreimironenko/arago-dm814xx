require ti-firmware-dm814x.inc

PV = "05_02_00_44"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "c95097d15865c8dff1691371ec194671"
SRC_URI[firmware.sha256sum] = "c0e5d1028273862320098c0e1fb90ab2f01bbbb2064da8ac27aad5e2aab3b5f7"
