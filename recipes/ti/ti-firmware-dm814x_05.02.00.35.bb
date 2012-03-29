require ti-firmware-dm814x.inc

PV = "05_02_00_35"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "4a6107baa4b6404e73d718c60ddfabd2"
SRC_URI[firmware.sha256sum] = "22e946afd69e55990e5ab57ccb1441f392195abb1012887355627f5f76440a5e"
