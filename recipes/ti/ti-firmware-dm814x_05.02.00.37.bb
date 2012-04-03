require ti-firmware-dm814x.inc

PV = "05_02_00_37"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "8b43e5b767048daeef3e03e13f1086fb"
SRC_URI[firmware.sha256sum] = "1e561d9dd1d3813d5b1ee607ac5bc1d9a279f72816a90bf3af1ddadd88e0a860"
