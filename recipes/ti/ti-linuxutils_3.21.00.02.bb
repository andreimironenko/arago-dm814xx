require ti-linuxutils.inc

PE = "1"
PV = "3_21_00_02"
PVExtra = "_eng"

# This is an internal engineering release. Do not use this release by default.
DEFAULT_PREFERENCE = "-1"
HTTP_PROXY_IGNORE = "www.sanb.design.ti.com"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Linux_Utils/${PV}/exports/linuxutils_${PV}${PVExtra}.tar.gz"

SRC_URI[md5sum] = "b242f7a8692e07d8a910934323d5d797"
SRC_URI[sha256sum] = "90e5e2b57e8ab920857cf7a2a550d8f8a82dfa445c6367fb17479e6732474d92"

