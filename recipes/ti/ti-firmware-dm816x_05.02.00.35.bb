require ti-firmware-dm816x.inc

PV = "05_02_00_35"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "880381bbb9ad1e245ad7de308195de7a"
SRC_URI[firmware.sha256sum] = "09e8dd6295a08c1cd34d9ab87a379899afc5e7a4cd45d98430b95e03f9c5be3f"
