require ti-media-controller-hdvpss-loader.inc

PV = "2_02_00_11"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/656/5058/media_controller_utils_${PV}.tar.gz"

SRC_URI[md5sum] = "e4676550a1036ac89c8832acedf625db"
SRC_URI[sha256sum] = "145a9653bdd05081c3905d054e793596bd3a8f5a56f972bed5ba39866f20ffa8"

S = "${WORKDIR}/media_controller_utils_${PV}/src/linux/${PLATFORM}"
