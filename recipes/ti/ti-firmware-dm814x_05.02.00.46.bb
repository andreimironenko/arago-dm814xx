require ti-firmware-dm814x.inc

PV = "05_02_00_46"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "058659b26492fd2a7afaed40a7283885"
SRC_URI[firmware.sha256sum] = "1f13fbdb3a38821547b841abb9a1ce6ad74c0b09dbb6dc64223bf89664ca2934"
