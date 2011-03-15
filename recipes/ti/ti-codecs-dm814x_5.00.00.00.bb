DESCRIPTION = "TI Codecs for TI814x"
SECTION = "multimedia"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

PR="${MACHINE_KERNEL_PR}"
PR_append = "a"

PV="5_00_00_00"

CODEC_SUITE_NAME="${WORKDIR}/${PN}_${PV}"

SRC_URI = "http://install.source.dir.local/ivahd_h264dec_01_00_00_00_production.tar;name=h264dec \
           http://install.source.dir.local/ivahd_h264enc_01_00_00_00_production.tar;name=h264enc \

"

SRC_URI[h264dec.md5sum] = "b6b002eefdcf7ee0e213b2bb184d1b38"
SRC_URI[h264dec.sha256sum] = "bb683dc5f7fecd3122c2af6e1942a58cd105f29b34654fd8b60e71dc9436d853"

SRC_URI[h264enc.md5sum] = "ddd866095d157e685e5c6e04500b3c79"
SRC_URI[h264enc.sha256sum] = "e89040bfc81291b2f321f5b835f0e44ad61c0eeccfe13aff019112e97aef11df"

S = "${CODEC_SUITE_NAME}"

addtask prepsources after do_unpack before do_patch

do_prepsources () {
    cd ${WORKDIR}
#    tar -xf "${WORKDIR}/ivahd_h264dec_01_00_00_00_production.tar"
#    tar -xf "${WORKDIR}/ivahd_h264enc_01_00_00_00_production.tar"

    mkdir -p ${CODEC_SUITE_NAME}/packages/ti/sdo/codecs

    cp -a "${WORKDIR}/ivahd_h264dec_01_00_00_00_production/packages/ti/sdo/codecs/h264dec" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    cp -a "${WORKDIR}/ivahd_h264enc_01_00_00_00_production/packages/ti/sdo/codecs/h264enc" "${CODEC_SUITE_NAME}/packages/ti/sdo/codecs"
    chmod 755 -R ${CODEC_SUITE_NAME}
}

do_install() {
    install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
    cp -pPrf ${CODEC_SUITE_NAME}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}
