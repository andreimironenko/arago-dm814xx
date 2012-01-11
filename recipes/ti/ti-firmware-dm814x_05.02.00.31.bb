require ti-firmware-dm814x.inc

PV = "05_02_00_31"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "62218eb55924ca5e869a099d032b985f"
SRC_URI[firmware.sha256sum] = "130c9d4dd6f3331817278fcfc6aaa42287004575b0a13f79c3450dbc70b521ce"
