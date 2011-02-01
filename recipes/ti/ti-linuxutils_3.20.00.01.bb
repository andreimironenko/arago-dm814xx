require ti-linuxutils.inc

PE = "1"
PV = "3_20_00_01"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Linux_Utils/${PV}/exports/linuxutils_${PV}${PVExtra}.tar.gz"

SRC_URI[md5sum] = "3e997ddbf1f04763a57cf32263eb5779"
SRC_URI[sha256sum] = "dbe42c7fd09e9150f0bb06c7a8c246bd77691b22c4723979c9d3ee89c3cc848e"

