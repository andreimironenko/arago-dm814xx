require ti-media-controller-utils.inc

PV = "3_00_00_04"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/884/5798/media-controller-utils_${PV}.tar.gz \
           file://0001-src-linux-ti811x-add-temporary-patch-for-load-hd-fir.patch \
"

SRC_URI[md5sum] = "ee94fbcfb1199f8a83b1a292c8d09847"
SRC_URI[sha256sum] = "ab0101a8e61fa2cae9a55ed7816a0e264b6c7e0147f6b96675e9e90e6428960a"

S = "${WORKDIR}/media-controller-utils_${PV}"
