require ti-media-controller-utils.inc

PV = "2_02_00_07"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/624/4904/media_controller_utils_${PV}.tar.gz"

SRC_URI[md5sum] = "770382920f4ca0407e3e629b926abd80"
SRC_URI[sha256sum] = "2853275c16678cd0ce8a45dc9bbd164121e3d9402e6ef7bd65b0407ae6690487"

S = "${WORKDIR}/media_controller_utils_${PV}"
