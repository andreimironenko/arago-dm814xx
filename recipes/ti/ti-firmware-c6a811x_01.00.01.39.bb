require ti-firmware-c6a811x.inc

PV = "01_00_01_39_sdk2"

SRC_URI = "http://install.source.dir.local/firmware-ti811x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "8690fb19a2824824910d6afbfc37a4cf"
SRC_URI[firmware.sha256sum] = "6fce85701daf433b210aff753f1c76c4e53f36136cd1bd9c068a93b51294a34e"
