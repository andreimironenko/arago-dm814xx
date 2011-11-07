DESCRIPTION = "TI AAC LC Decoder for C674x devices"
SECTION = "multimedia"
LICENSE = "TI Proprietary"

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc

PV="01_41_00_00"
PVExtra="_elf"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/codecs/C674X_Audio_Codecs/01_00_001/exports/c674x_aaclcdec_${PV}${PVExtra}.bin \
"

SRC_URI[md5sum] = "c98b5a091bc1e9bdf0678a2b1bd21c84"
SRC_URI[sha256sum] = "2f4e6cf35cb74854bf6eac4e282e069c7b7cf4f4e6c44e7cba43d7789b4b54e9"

TI_BIN_UNPK_CMDS = "y:q y:workdir"
BINFILE = c674x_aaclcdec_${PV}${PVExtra}.bin 

S = ${WORKDIR}/c674x_aaclcdec_${PV}${PVExtra}

do_install() {
    install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}
