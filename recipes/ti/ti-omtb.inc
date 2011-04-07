DESCRIPTION = "TI OMX Test Bench"
SECTION = "devel"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/omtb_${PV}${PVExtra}"

SRC_URI = "http://install.source.dir.local/omtb_${PV}${PVExtra}.tar.gz;name=omtb"

DEPENDS = "ti-omx"

do_compile() {
     :
}

do_install() {
     :
}
