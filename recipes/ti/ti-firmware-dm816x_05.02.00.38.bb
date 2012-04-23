require ti-firmware-dm816x.inc

PV = "05_02_00_38"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "71cb09a71b4803449425f249e6201cd2"
SRC_URI[firmware.sha256sum] = "f3e69a860113f51f9460255d9cca6fad097aaee79f82213a34536ef7c74c063d"
