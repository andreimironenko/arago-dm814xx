DESCRIPTION = "TI Codecs for TI814x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR="${MACHINE_KERNEL_PR}"
PR_append = "a"

PV="5_02_00_00"

CODEC_SUITE_NAME="${WORKDIR}/${PN}_${PV}"

SRC_URI = "http://install.source.dir.local/ivahd_h264vdec_02_00_07_00_production.tar;name=h264dec \
           http://install.source.dir.local/ivahd_h264enc_02_00_02_02_production.tar;name=h264enc \
           http://install.source.dir.local/ivahd_mpeg2vdec_01_00_01_00_production.tar;name=mpeg2vdec \
           http://install.source.dir.local/c674x_aaclcdec_01_41_00_00_elf.tar;name=aaclcdec \
"

SRC_URI[h264dec.md5sum] = "e8f32bd837da08e8e8ec8fc68a6d040b"
SRC_URI[h264dec.sha256sum] = "39e414a71291e1f5b28184d7f68424c2d8140cb8efe9111e03852b967bd5a4ca"

SRC_URI[h264enc.md5sum] = "ba275f251e2fa1b6c5c5a7ef8d761753"
SRC_URI[h264enc.sha256sum] = "9dfe52643d48ef67d81c42c73c12e7f09182758c39a826d9e9402f52bb73c875"

SRC_URI[mpeg2vdec.md5sum] = "851d2f59fa2f8888a23f0ee19066875d"
SRC_URI[mpeg2vdec.sha256sum] = "8283b06bc2d509b455690dd830b2fb18d172ddead2ab09fa26a37e85f84241e1"

SRC_URI[aaclcdec.md5sum] = "a167c6965858e1f7547f57bbc45baef1"
SRC_URI[aaclcdec.sha256sum] = "f95c6cb4cf0cf09af85902de3eb3aa5c7aa0b3681fd82bbb78985e237e1db1a0"

S = "${CODEC_SUITE_NAME}"

addtask prepsources after do_unpack before do_patch

do_prepsources () {
    cd ${WORKDIR}

    mkdir -p ${CODEC_SUITE_NAME}/packages/ti/sdo/codecs

    cp -a "${WORKDIR}/ivahd_h264vdec_02_00_07_00_production/packages/ti/sdo/codecs/h264vdec" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    cp -a "${WORKDIR}/ivahd_h264enc_02_00_02_02_production/packages/ti/sdo/codecs/h264enc" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    cp -a "${WORKDIR}/ivahd_mpeg2vdec_01_00_01_00_production/packages/ti/sdo/codecs/mpeg2vdec" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    cp -a "${WORKDIR}/c674x_aaclcdec_01_41_00_00_elf/packages/ti/sdo/codecs/aaclcdec" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    chmod 755 -R ${CODEC_SUITE_NAME}
}

do_install() {
    install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
    cp -pPrf ${CODEC_SUITE_NAME}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}
