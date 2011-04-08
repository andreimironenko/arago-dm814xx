DESCRIPTION = "TI OMX Test Bench"
SECTION = "devel"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/omtb_${PV}${PVExtra}"

SRC_URI = "http://install.source.dir.local/omtb_${PV}${PVExtra}.tar.gz;name=omtb"

DEPENDS_dm816x-custom = "ti-omx"
DEPENDS_dm814x-custom = "ti-omx"
DEPENDS_dm816x-evm = "ti-omx-libs"
DEPENDS_dm814x-evm = "ti-omx-libs"

do_compile() {
     :
}

do_install() {
     :
}
