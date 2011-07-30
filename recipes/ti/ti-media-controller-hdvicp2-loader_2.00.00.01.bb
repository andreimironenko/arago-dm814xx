require ti-media-controller-hdvicp2-loader.inc

PV = "2_00_00_01"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/587/4846/media_controller_utils_2_00_00_01.tar.gz"

SRC_URI[md5sum] = "97771b13af32f27223b1141dddf662ed"
SRC_URI[sha256sum] = "6c3bd6f124d668dc90e4eff505f4d083584338e5f51de74d16c7485afa6c23e8"

S = "${WORKDIR}/media_controller_utils_${PV}/src/linux/${PLATFORM}"
