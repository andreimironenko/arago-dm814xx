require ti-xdais.inc

PV = "7_21_01_05"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/XDAIS/${PV}/exports/xdais_${PV}${PVExtra}.tar.gz;name=xdaistarball \
           file://0001-Added-sdk-make-install-file-to-xdais.patch \
"

SRC_URI[xdaistarball.md5sum] = "a1027a68b1fdbe00bf5806a94f1480bc"
SRC_URI[xdaistarball.sha256sum] = "caad38d27daf7e52183b87bf7fbba5830e9c656a0de93841d0247d52324407c3"
