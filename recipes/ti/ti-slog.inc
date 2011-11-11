DESCRIPTION = "TI SLOG"
SECTION = "devel"
LICENSE = "EPL"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/Slog_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_tii/Slog/${PV}/exports/Slog_${PV}.tar.gz"

do_install() {
    install -d ${D}${SLOG_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${SLOG_INSTALL_DIR_RECIPE}
}

