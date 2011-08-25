require ti-media-controller-hdvpss-loader.inc

PV = "2_02_00_09"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/639/4954/media_controller_utils_${PV}.tar.gz"

SRC_URI[md5sum] = "36d2d72cb12d50376f9882aef1342439"
SRC_URI[sha256sum] = "d27bc8803a0475291950a9b24573faebf11a0c0f05539575531a944342ee924c"

S = "${WORKDIR}/media_controller_utils_${PV}/src/linux/${PLATFORM}"
