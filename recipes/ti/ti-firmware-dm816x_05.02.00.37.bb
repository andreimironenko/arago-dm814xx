require ti-firmware-dm816x.inc

PV = "05_02_00_37"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "11defaace676c53927945c820a705b10"
SRC_URI[firmware.sha256sum] = "fa6e1391028201b83cf78cd0d9b039a0771c2f9cb069bf9245ec265b69f8c245"
