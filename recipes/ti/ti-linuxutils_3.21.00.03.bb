require ti-linuxutils.inc

PE = "1"
PV = "3_21_00_03"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Linux_Utils/${PV}/exports/linuxutils_${PV}${PVExtra}.tar.gz"

SRC_URI[md5sum] = "f1a85e7a13e91fe9d5e485bd7ccc082c"
SRC_URI[sha256sum] = "d682f99349c5f1fa496a225a3f91598118ae1980d8d2791f22aeca54331d811e"
