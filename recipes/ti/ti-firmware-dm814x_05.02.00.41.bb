require ti-firmware-dm814x.inc

PV = "05_02_00_41"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "028d06d61ddde2c295404b1d245208cd"
SRC_URI[firmware.sha256sum] = "8e00a6442cb8b5a5e8d1ebd465bffabd14f6c1dc045d426525ee7cf07eddf9c4"

