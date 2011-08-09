require ti-media-controller-hdvpss-loader.inc

PV = "2_01_00_05"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/606/4883/media_controller_utils_2_01_00_05.tar.gz"

SRC_URI[md5sum] = "06a30f0eac5bd7b9b72215bf3706c43d"
SRC_URI[sha256sum] = "3ad87e1904a900124b8946bf616e9c3e19f0011250fc17153efa77633f8dd4b5"

S = "${WORKDIR}/media_controller_utils_${PV}/src/linux/${PLATFORM}"
