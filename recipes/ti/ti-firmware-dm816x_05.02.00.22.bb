require ti-firmware-dm816x.inc

PV = "05_02_00_22"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "ca8a49512c62e6ae399385f66f7328c0"
SRC_URI[firmware.sha256sum] = "aed334ef224dc565e514a30b73fef7e45b9047798d871eea6696df55a6e1ee97"
