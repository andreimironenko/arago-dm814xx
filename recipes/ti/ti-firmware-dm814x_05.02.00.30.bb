require ti-firmware-dm814x.inc

PV = "05_02_00_30"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "e73732007b4182b2c6deb25fb2e5df35"
SRC_URI[firmware.sha256sum] = "83e05c3066a2eba0aeea9279b956e5858b6309350084284798538fb8e388c851"
