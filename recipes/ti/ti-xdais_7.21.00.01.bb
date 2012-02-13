require ti-xdais.inc

PV = "7_21_00_01"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/XDAIS/${PV}/exports/xdais_${PV}${PVExtra}.tar.gz;name=xdaistarball"

SRC_URI[xdaistarball.md5sum] = "761f373dc51deb5d965f026ddb72e290"
SRC_URI[xdaistarball.sha256sum] = "bdb5d0917d0a09f45044e285097b1ab7293a67e10c11b1466bdc1525cdaf7187"
