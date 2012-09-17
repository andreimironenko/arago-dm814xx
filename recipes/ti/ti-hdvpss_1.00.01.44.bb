require ti-hdvpss.inc

PV = "01_00_01_44"

S = "${WORKDIR}/hdvpss_${PV}"

SRC_URI = "http://install.source.dir.local/hdvpss_${PV}.tar.gz;name=hdvpss"

SRC_URI[hdvpss.md5sum] = "c19c30d11e4671d9def2ed1aa7e13285"
SRC_URI[hdvpss.sha256sum] = "7c7bb1f67bafcb0cc549ac338400c1cd87a0e6fbf90ac9d10acbd19663c116a2"

