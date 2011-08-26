DESCRIPTION = "TI Codecs for TI81xx"
SECTION = "multimedia"
LICENSE = "TI TSPA"

require ti-paths.inc
require ti-staging.inc

PV="01_41_00_00"
PVExtra="_elf"

SRC_URI = "http://install.source.dir.local/c674x_aaclcdec_${PV}${PVExtra}.tar;name=aaclcdec \
"

S = "${WORKDIR}/c674x_aaclcdec_${PV}${PVExtra}"

SRC_URI[aaclcdec.md5sum] = "a167c6965858e1f7547f57bbc45baef1"
SRC_URI[aaclcdec.sha256sum] = "f95c6cb4cf0cf09af85902de3eb3aa5c7aa0b3681fd82bbb78985e237e1db1a0"
