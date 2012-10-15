require ti-firmware-c6a811x.inc

PV = "01_00_01_44"

SRC_URI = "http://install.source.dir.local/firmware-ti811x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "9e5ac9bf419302a0aaa136b81b4e817c"
SRC_URI[firmware.sha256sum] = "43d74e6145253af6bae92b7926363e79dc3d6b0bfa4cdca90ba08b17bb77ecc5"
