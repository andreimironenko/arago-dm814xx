require ti-firmware-c6a811x.inc

PV = "01_00_01_39"

SRC_URI = "http://install.source.dir.local/firmware-ti811x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "41b62ef184b34d5f48af48369bf745cd"
SRC_URI[firmware.sha256sum] = "7e908b5afc2bf78220ba1de2fda84159b42a3c6a593c792b0768a730beb681d9"
