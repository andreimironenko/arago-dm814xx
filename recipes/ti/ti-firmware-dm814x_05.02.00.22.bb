require ti-firmware-dm814x.inc

PV = "05_02_00_22"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "b193856537c22ee39152addfc5e186e6"
SRC_URI[firmware.sha256sum] = "fc20660407ef15e983af7bce7ead14add34a66fd3092f894767f1d7cfd0f7655"
