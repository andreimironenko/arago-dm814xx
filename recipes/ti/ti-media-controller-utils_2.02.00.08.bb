require ti-media-controller-utils.inc

PV = "2_02_00_08"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/627/4923/media_controller_utils_${PV}.tar.gz"

SRC_URI[md5sum] = "3aca2a22647ba3db82e2e34a8a6c98f1"
SRC_URI[sha256sum] = "b910b26d334bd1d59181392963c069d28bfd784a5fe8f418ce7c77f831dbc38b"

S = "${WORKDIR}/media_controller_utils_${PV}"
