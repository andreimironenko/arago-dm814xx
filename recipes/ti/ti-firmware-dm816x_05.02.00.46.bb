require ti-firmware-dm816x.inc

PV = "05_02_00_46"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "875632191c0c7720eba385661d3a52e7"
SRC_URI[firmware.sha256sum] = "a71387795fb6edd8cf15d6024ddad5a49c2e6c007adf37692f70f4b9748fb7ba"
