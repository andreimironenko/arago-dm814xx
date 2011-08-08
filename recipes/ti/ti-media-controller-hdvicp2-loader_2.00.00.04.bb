require ti-media-controller-hdvicp2-loader.inc

PV = "2_00_00_04"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/604/4879/media_controller_utils_2_00_00_04.tar.gz"

SRC_URI[md5sum] = "92f6ae83ed5a0483cef240932775326c"
SRC_URI[sha256sum] = "718ac90c2155ccb3373bc3372f56e85aac3f78fe57afc771335d31482d98c9f4"

S = "${WORKDIR}/media_controller_utils_${PV}/src/linux/${PLATFORM}"
