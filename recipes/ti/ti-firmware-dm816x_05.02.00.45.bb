require ti-firmware-dm816x.inc

PV = "05_02_00_45"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "c1cb5e65859dc63321b0147e4a09cb14"
SRC_URI[firmware.sha256sum] = "a5375d729954a29942f37026142f2614bc5e309de7f724b38e1a9b6dca7d366d"
