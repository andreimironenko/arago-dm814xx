require ti-firmware-dm814x.inc

PV = "05_02_00_45"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "7d4717c59b3dee3a70ed1c9ec05d7c8b"
SRC_URI[firmware.sha256sum] = "62934a865c028661d5763a8f1bfd2f3cd832f98331c4be00270298d51f354ec8"
