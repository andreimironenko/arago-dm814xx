require ti-media-controller-utils.inc

PV = "2_03_00_12"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/709/5164/media_controller_utils_2_03_00_12.tar.gz"

SRC_URI[md5sum] = "afd3cb8545e10bebed13e82ea2a25d1d"
SRC_URI[sha256sum] = "30334497be1b85afef6bfa0983f25b8bd3bbfc0bf627b0c78837cf76cd7e6029"

S = "${WORKDIR}/media_controller_utils_${PV}"
