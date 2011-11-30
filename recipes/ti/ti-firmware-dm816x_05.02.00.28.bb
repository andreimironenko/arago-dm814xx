require ti-firmware-dm816x.inc

PV = "05_02_00_28"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "8fc09ff8087e8c9f0914bde7ba0c88af"
SRC_URI[firmware.sha256sum] = "52459d4059ac088d14a0a6a70d1a6f57de2b537ef320668f265e184a47c352b1"
