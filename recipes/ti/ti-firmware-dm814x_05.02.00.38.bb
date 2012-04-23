require ti-firmware-dm814x.inc

PV = "05_02_00_38"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "1b233987d3b300b94aaf4b584013ad75"
SRC_URI[firmware.sha256sum] = "84b3baa5798e07336c0f99db35d7a1d4aac7ec84ad9fac434f3e2fa6f8e553a9"
