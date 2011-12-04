require ti-firmware-dm816x.inc

PV = "05_02_00_29"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM816x-Firmware/${PV}/exports/firmware-ti816x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "8ce1ad769bf3696459ba8cf00efc4dfe"
SRC_URI[firmware.sha256sum] = "d5ccd6e9c6ebee6b7b5698e425c969bfc5ce81d5712b753f959ee93a658d17f2"
