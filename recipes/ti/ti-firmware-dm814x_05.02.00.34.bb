require ti-firmware-dm814x.inc

PV = "05_02_00_34"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "9e176c7866b69fc398ce3dbddfbf3226"
SRC_URI[firmware.sha256sum] = "33f544755c56fcca5e2fe1a6a32cacd4ea11344ca3f0b8506fc72dbfe144ea71"
