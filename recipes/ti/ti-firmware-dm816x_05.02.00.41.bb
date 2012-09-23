require ti-firmware-dm816x.inc

PV = "05_02_00_41"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "07106470c1a0e3ae00b15dd5ad3a09ec"
SRC_URI[firmware.sha256sum] = "ed1ecec1dc3ab53323ba326c0e0d34446c1a034428e00c215716d857646f7a60"
