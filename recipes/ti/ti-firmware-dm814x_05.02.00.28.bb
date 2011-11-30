require ti-firmware-dm814x.inc

PV = "05_02_00_28"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "f4982552da038ea14e9c69758c7edbe3"
SRC_URI[firmware.sha256sum] = "0595e3843d2cd849dc71cdd29f273d0befe24373553d0e8c7574515ca80a8e8d"
