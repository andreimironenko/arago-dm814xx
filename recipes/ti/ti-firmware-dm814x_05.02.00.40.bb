require ti-firmware-dm814x.inc

PV = "05_02_00_40"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "11f51e4b665e63f9c9b835c5a54af143"
SRC_URI[firmware.sha256sum] = "74727aeaa1a19ca12b87d33734a5fd88bf4d0fdb633a93b218e9b95f8442bb54"

