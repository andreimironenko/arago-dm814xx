require ti-media-controller-hdvicp2-loader.inc

PV = "2_02_00_10"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/646/4986/media_controller_utils_${PV}.tar.gz"

SRC_URI[md5sum] = "a6a5dd410c892dc4ca36d476412ea7d4"
SRC_URI[sha256sum] = "790553ff00d6b297299a929f9f504534ee5d778826c52b432bd7dc88e1b69c49"

S = "${WORKDIR}/media_controller_utils_${PV}/src/linux/${PLATFORM}"
