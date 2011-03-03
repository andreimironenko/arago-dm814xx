DESCRIPTION = "TI Codecs for TI814x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR="${MACHINE_KERNEL_PR}"
PR_append = "a"

PV="5_00_00_00"

CODEC_SUITE_NAME="${WORKDIR}/${PN}_${PV}"

SRC_URI = "http://install.source.dir.local/REL.500.V.H264AVC.D.HP.IVAHD.01.00.00.00.zip;name=h264dec \
           http://install.source.dir.local/REL.500.V.H264AVC.E.IVAHD.01.00.00.00.zip;name=h264enc \

"

SRC_URI[h264dec.md5sum] = "b555a33e9e99d42c1aa9c5ce9992c396"
SRC_URI[h264dec.sha256sum] = "146ac8a116c45d5fd95fd5a51cc0b9e833bd32b2958cba4cffe9e153bead15dc"

SRC_URI[h264enc.md5sum] = "d2e19b1b3aa61f33759ea772c4c5012e"
SRC_URI[h264enc.sha256sum] = "108c96e9dfd9f68eb93859cf1b4ce11ad1483660c062dba3e11af49cfa62c9dc"

S = "${CODEC_SUITE_NAME}"

addtask prepsources after do_unpack before do_patch

do_prepsources () {
    cd ${WORKDIR}
    tar -xf "${WORKDIR}/REL.500.V.H264AVC.D.HP.IVAHD.01.00.00.00/ivahd_h264dec_01_00_00_00_production.tar"
    tar -xf "${WORKDIR}/REL.500.V.H264AVC.E.IVAHD.01.00.00.00/ivahd_h264enc_01_00_00_00_production.tar"

    mkdir -p ${CODEC_SUITE_NAME}/packages/ti/sdo/codecs

    cp -a "${WORKDIR}/ivahd_h264dec_01_00_00_00_production/packages/ti/sdo/codecs/h264dec" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    cp -a "${WORKDIR}/ivahd_h264enc_01_00_00_00_production/packages/ti/sdo/codecs/h264enc" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    chmod 755 -R ${CODEC_SUITE_NAME}
}

do_install() {
    install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
    cp -pPrf ${CODEC_SUITE_NAME}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}
