require ti-hdvpss.inc

PV = "01_00_01_39"

S = "${WORKDIR}/hdvpss_${PV}"

SRC_URI = "http://install.source.dir.local/hdvpss_${PV}.tar.gz;name=hdvpss"

SRC_URI[hdvpss.md5sum] = "1eb90f2acd22da5a44563fb94c5c0aa0"
SRC_URI[hdvpss.sha256sum] = "7a7e77c8bc434892147a3b8ab9919196486097cf2a9eb6ec9cbb669a0e8a18a9"
