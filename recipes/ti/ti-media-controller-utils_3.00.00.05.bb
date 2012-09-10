require ti-media-controller-utils.inc

PV = "3_00_00_05"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/884/5798/media-controller-utils_${PV}.tar.gz \
"

SRC_URI[md5sum] = "69456d9431831da5017f781b108602f4"
SRC_URI[sha256sum] = "621c7571db06445aa8a46ef4097afe8ed8b97dd4ee3917087052539f8b8cafcb"

S = "${WORKDIR}/media-controller-utils_${PV}"
