require ti-media-controller-hdvicp2-loader.inc

PV = "2_00_00_02"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/590/4852/media_controller_utils_2_00_00_02.tar.gz"

SRC_URI[md5sum] = "df5630855ca724ba8b307b10a3b07067"
SRC_URI[sha256sum] = "fcce8eee6dac7a0be652990a87d7eb168079b80bc8328dbe4a8752d700986705"

S = "${WORKDIR}/media_controller_utils_${PV}/src/linux/${PLATFORM}"
