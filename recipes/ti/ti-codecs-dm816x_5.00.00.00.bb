DESCRIPTION = "TI Codecs for TI816x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR="${MACHINE_KERNEL_PR}"
PR_append = "a"

PV="5_00_00_00"

CODEC_SUITE_NAME="${WORKDIR}/${PN}_${PV}"

SRC_URI = "http://install.source.dir.local/REL.500.V.H264AVC.D.HP.IVAHD.00.07.00.01.zip;name=h264dec \
           http://install.source.dir.local/REL.500.V.H264AVC.E.IVAHD.00.08.00.01.zip;name=h264enc \

"

SRC_URI[h264dec.md5sum] = "150c3fdfb9c70bb0f14d95fe2916d062"
SRC_URI[h264dec.sha256sum] = "794b06e258ef6336ec66bd1b7a913a0d679aa5387b0c2207dc1d05eb3c060de9"

SRC_URI[h264enc.md5sum] = "180084a0c29c1c0b34fc933fae9c57b2"
SRC_URI[h264enc.sha256sum] = "88c712238d781029a45bd03d93d5590b42c162924ae439cc150916899679d219"

S = "${CODEC_SUITE_NAME}"

addtask prepsources after do_unpack before do_patch

do_prepsources () {
    cd ${WORKDIR}
    tar -xf "${WORKDIR}/REL.500.V.H264AVC.D.HP.IVAHD.00.07.00.01/ivahd_h264dec_00_07_00_01_production.tar"
    tar -xf "${WORKDIR}/REL.500.V.H264AVC.E.IVAHD.00.08.00.01/ivahd_h264enc_00_08_00_01_production.tar"

    mkdir -p ${CODEC_SUITE_NAME}/packages/ti/sdo/codecs

    cp -a "${WORKDIR}/ivahd_h264dec_00_07_00_01_production/packages/ti/sdo/codecs/h264dec" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    cp -a "${WORKDIR}/ivahd_h264enc_00_08_00_01_production/packages/ti/sdo/codecs/h264enc" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    chmod 755 -R ${CODEC_SUITE_NAME}
}

do_install() {
    install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
    cp -pPrf ${CODEC_SUITE_NAME}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}
