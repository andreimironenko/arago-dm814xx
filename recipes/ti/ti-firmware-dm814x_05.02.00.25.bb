require ti-firmware-dm814x.inc

PV = "05_02_00_25"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "2360f3cbc302a7d927e8480e9ad4d561"
SRC_URI[firmware.sha256sum] = "6e9692743b83d79d8a3641099478b03ed8366c0a5cb9cf32e3e36da6ca62f967"
