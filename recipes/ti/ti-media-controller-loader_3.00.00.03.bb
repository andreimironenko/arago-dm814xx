require ti-media-controller-loader.inc

PV = "3_00_00_03"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/881/5788/media-controller-utils_${PV}.tar.gz \
           file://load-hd-firmware.sh \
"

SRC_URI[md5sum] = "90802c4b9b82c27751e8b67a26b63666"
SRC_URI[sha256sum] = "85fe26527521686db55bf2cb86eb8990b57ed273058c99ac0fe1116732dacbd2"

S = "${WORKDIR}/media-controller-utils_${PV}/src/linux/${PLATFORM}"

do_copyimage() {
    cp -f ${WORKDIR}/load-hd-firmware.sh ${S}
}
addtask copyimage after do_unpack before do_patch
