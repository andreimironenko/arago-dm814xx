DESCRIPTION = "TI Codecs for TI816x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR="${MACHINE_KERNEL_PR}"
PR_append = "a"

PV="5_02_00_00"

CODEC_SUITE_NAME="${WORKDIR}/${PN}_${PV}"

SRC_URI = "http://install.source.dir.local/ivahd_h264dec_01_00_00_02_production.tar;name=h264dec \
           http://install.source.dir.local/ivahd_h264enc_01_00_01_03_production.tar;name=h264enc \
           http://install.source.dir.local/ivahd_mpeg2vdec_01_00_01_00_production.tar;name=mpeg2vdec \
           http://install.source.dir.local/c674x_aaclcdec_01_41_00_00_elf.tar;name=aaclcdec \
"

SRC_URI[h264dec.md5sum] = "5a968b9fbb2026a21cf8a08986c50355"
SRC_URI[h264dec.sha256sum] = "59324d0b12fd6c282f0b2a33d5d52e0570d61a7c4af725290ab533b973dfe7cd"

SRC_URI[h264enc.md5sum] = "f94a26dca78bfcc468e3bc7dd26904d3"
SRC_URI[h264enc.sha256sum] = "0811880c48da42918d95a4fdebd7ea41de2f1f960d0ab14fe617bd579506f135"

SRC_URI[mpeg2vdec.md5sum] = "851d2f59fa2f8888a23f0ee19066875d"
SRC_URI[mpeg2vdec.sha256sum] = "8283b06bc2d509b455690dd830b2fb18d172ddead2ab09fa26a37e85f84241e1"

SRC_URI[aaclcdec.md5sum] = "a167c6965858e1f7547f57bbc45baef1"
SRC_URI[aaclcdec.sha256sum] = "f95c6cb4cf0cf09af85902de3eb3aa5c7aa0b3681fd82bbb78985e237e1db1a0"

S = "${CODEC_SUITE_NAME}"

addtask prepsources after do_unpack before do_patch

do_prepsources () {
    cd ${WORKDIR}

    mkdir -p ${CODEC_SUITE_NAME}/packages/ti/sdo/codecs

    cp -a "${WORKDIR}/ivahd_h264dec_01_00_00_02_production/packages/ti/sdo/codecs/h264dec" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    cp -a "${WORKDIR}/ivahd_h264enc_01_00_01_03_production/packages/ti/sdo/codecs/h264enc" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    cp -a "${WORKDIR}/ivahd_mpeg2vdec_01_00_01_00_production/packages/ti/sdo/codecs/mpeg2vdec" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    cp -a "${WORKDIR}/c674x_aaclcdec_01_41_00_00_elf/packages/ti/sdo/codecs/aaclcdec" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    chmod 755 -R ${CODEC_SUITE_NAME}
}

do_install() {
    install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
    cp -pPrf ${CODEC_SUITE_NAME}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}
