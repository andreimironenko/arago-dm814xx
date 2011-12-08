require ti-media-controller-utils.inc

PV = "2_03_01_14"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/760/5288/media_controller_utils_2_03_01_14.tar.gz \
           file://0001-linux-Install-all-shell-scripts.patch \
"

SRC_URI[md5sum] = "3c3ffa09a31782b15c65247cc6cbe43c"
SRC_URI[sha256sum] = "bd558f688d0a9d5d96a6d1ce3c5b5381cd95e881fdaf607377fb9023869fa907"

S = "${WORKDIR}/media_controller_utils_${PV}"
