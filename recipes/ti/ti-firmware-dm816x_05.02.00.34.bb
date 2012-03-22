require ti-firmware-dm816x.inc

PV = "05_02_00_34"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "1dcbbe34d815918ffd4e27afc3e7f66e"
SRC_URI[firmware.sha256sum] = "1fdfd591ef2b485578082ef68c6672e99838c422ad168a62656ad730e188c979"
