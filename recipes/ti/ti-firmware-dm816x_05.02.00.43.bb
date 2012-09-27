require ti-firmware-dm816x.inc

PV = "05_02_00_43"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "2bb1373bf96b89e2483a5e98ae776396"
SRC_URI[firmware.sha256sum] = "a54a40d4824e2acbaf717605266fa064eca3ff5bb76228c3ac97435294452213"
