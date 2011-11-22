require ti-firmware-dm814x.inc

PV = "05_02_00_27"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "21ad0187e1437caf5e7a3ec787ae5fbc"
SRC_URI[firmware.sha256sum] = "4505825e907814fafa5a3e4121c8c994870b0614392c2efda12b5f7ba00093d9"
