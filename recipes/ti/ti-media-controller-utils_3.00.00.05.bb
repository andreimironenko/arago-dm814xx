require ti-media-controller-utils.inc

PV = "3_00_00_05"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/884/5798/media-controller-utils_${PV}.tar.gz \
"

SRC_URI[md5sum] = "69456d9431831da5017f781b108602f4"
SRC_URI[sha256sum] = "621c7571db06445aa8a46ef4097afe8ed8b97dd4ee3917087052539f8b8cafcb"

#SRC_URI[md5sum] = "06b0a290f5ece71488584c6bb20db6a2"
#SRC_URI[sha256sum] = "5f5be0afe03ef9bfa23d34dfaea0be3cf7fa663c2124f024009c9386b90e1031"


S = "${WORKDIR}/media-controller-utils_${PV}"
