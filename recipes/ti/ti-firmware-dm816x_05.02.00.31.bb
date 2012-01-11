require ti-firmware-dm816x.inc

PV = "05_02_00_31"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "7735380ab0ea3b727af2494b08400b78"
SRC_URI[firmware.sha256sum] = "8fdac5afa4eea778abe9f5bba4c08c04798d245311c09bcf2971dfabd9631c9b"
