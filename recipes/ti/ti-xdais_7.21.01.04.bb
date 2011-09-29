require ti-xdais.inc

PV = "7_21_01_04"
PVExtra = "_eng"

SRC_URI[xdaistarball.md5sum] = "cefbaa8f22da64082b7767c86ea35c36"
SRC_URI[xdaistarball.sha256sum] = "aa11dccfc14f63a24a39cee8d924b914141249da99da70f8e785d537082c279d"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/XDAIS/${PV}/exports/xdais_${PV}${PVExtra}.tar.gz;name=xdaistarball \
           file://0001-Added-sdk-make-install-file-to-xdais.patch \
"
