require ti-firmware-dm816x.inc

PV = "05_02_00_44"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "f7a5758ada9cfb1b087a08fca0aa0fb0"
SRC_URI[firmware.sha256sum] = "76a4dd0afb329c19a3a0b67f498e3f1f7bab8466ef84c84c811398774cd777cf"
