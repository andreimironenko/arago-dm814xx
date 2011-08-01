DESCRIPTION = "TI SLOG"
SECTION = "devel"
LICENSE = "EPL"

require ti-paths.inc
require ti-staging.inc

PR = "r1"

S = "${WORKDIR}/Slog_${PV}"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/Slog/${PV}/exports/Slog_${PV}.tar.gz"

do_install() {
    install -d ${D}${SLOG_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${SLOG_INSTALL_DIR_RECIPE}
}

