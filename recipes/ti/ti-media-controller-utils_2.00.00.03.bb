require ti-media-controller-utils.inc

PV = "2_00_00_03"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/601/4870/media_controller_utils_2_00_00_03.tar.gz"

SRC_URI[md5sum] = "9c994d5653c0ea66509c08169070e030"
SRC_URI[sha256sum] = "1e7b7a7fb2f6c815a7314479f16a0866d9ae2927efdebc54ffa9f220b1dc32ad"

S = "${WORKDIR}/media_controller_utils_${PV}"
