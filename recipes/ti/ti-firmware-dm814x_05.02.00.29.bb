require ti-firmware-dm814x.inc

PV = "05_02_00_29"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "a7d2d4f14b0916bd4406ac4a73cb70a4"
SRC_URI[firmware.sha256sum] = "7bc4798e91bbfb607bfe13e5ae3536c4400cf64cc22a21e698f6ecd43bcd84bd"
