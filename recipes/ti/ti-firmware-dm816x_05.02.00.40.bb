require ti-firmware-dm816x.inc

PV = "05_02_00_40"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "0b338694f49d24ddbc28272dc87ce019"
SRC_URI[firmware.sha256sum] = "f88cb0afb1531bf82bcee80d4b1e46f92cefe39e7cd47c94d6dfdfecc75c1582"
