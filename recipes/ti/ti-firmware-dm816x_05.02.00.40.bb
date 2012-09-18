require ti-firmware-dm816x.inc

PV = "05_02_00_40"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "4001e203730bc802c2e8d80a8cdc2415"
SRC_URI[firmware.sha256sum] = "5acd35318c951035ab6c6a45de77a9799ebea9e1413661eed9d8908a327104c7"
