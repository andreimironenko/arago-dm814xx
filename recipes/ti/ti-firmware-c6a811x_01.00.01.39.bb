require ti-firmware-c6a811x.inc

PV = "01_00_01_39_sdk1"

SRC_URI = "http://install.source.dir.local/firmware-ti811x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "ca283eee7786a113c287bd9584b3e439"
SRC_URI[firmware.sha256sum] = "ab74f202c5570d19eee1e48c88dc1a7fcda1730d60f74d8e224e1e52add0b933"
