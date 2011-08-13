require ti-media-controller-hdvpss-loader.inc

PV = "2_01_00_06"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/623/4903/media_controller_utils_${PV}.tar.gz"

SRC_URI[md5sum] = "29f8003199a460c4d219e8402a64f09a"
SRC_URI[sha256sum] = "47bc12141a3e233acd5a128c66647275dd7298512103601762b6a85f95020eb2"


S = "${WORKDIR}/media_controller_utils_${PV}/src/linux/${PLATFORM}"
