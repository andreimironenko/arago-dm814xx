require ti-media-controller-loader.inc

PV = "2_05_00_16"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/826/5493/media-controller-utils_${PV}.tar.gz"

SRC_URI[md5sum] = "033bfde756b2f3964f7f586c8a8ddb67"
SRC_URI[sha256sum] = "db362a2120e185e13cb86426626e371c6d6d9c466a311162f59c10683c749cd1"

S = "${WORKDIR}/media-controller-utils_${PV}/src/linux/${PLATFORM}"
