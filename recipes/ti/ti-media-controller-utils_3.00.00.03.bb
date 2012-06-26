require ti-media-controller-utils.inc

PV = "3_00_00_03"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/881/5788/media-controller-utils_${PV}.tar.gz \
           file://0001-src-linux-ti811x-add-temporary-patch-for-load-hd-fir.patch \
"

SRC_URI[md5sum] = "90802c4b9b82c27751e8b67a26b63666"
SRC_URI[sha256sum] = "85fe26527521686db55bf2cb86eb8990b57ed273058c99ac0fe1116732dacbd2"

S = "${WORKDIR}/media-controller-utils_${PV}"
