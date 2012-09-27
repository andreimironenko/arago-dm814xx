require ti-firmware-dm814x.inc

PV = "05_02_00_43"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "a9dd7d841a33cf2cca9cff53445d10f5"
SRC_URI[firmware.sha256sum] = "ecc54d8643cf5b0158dfd7cdd0e330ac5749bba56b3cdccf5d50631e9f3d0f76"
