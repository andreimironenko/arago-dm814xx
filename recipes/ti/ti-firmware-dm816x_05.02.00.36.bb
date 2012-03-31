require ti-firmware-dm816x.inc

PV = "05_02_00_36"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "de4b0481c8e1339fd14d936c5ffee582"
SRC_URI[firmware.sha256sum] = "d075e9c7ec7c73c012fee2558957bd3f41cac6913d232fdcb1bcc32e3d04d669"
