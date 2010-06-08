DESCRIPTION = "TI Codecs for DM355"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent"
SECTION = "multimedia"
LICENSE = "TI"

# TODO :: Move to common .inc

PV = "03_10_00_01"

SRC_URI[dm355codecsbin.md5sum] = "405c7b4fb10bff60b6cde8c2f6633726"
SRC_URI[dm355codecsbin.sha256sum] = "d552d785895413d15724388d04958ae85db0576ad4ea09af0d7156962b9ffdf1"

PR = "r1"

require ti-paths.inc
require ti-staging.inc

PROVIDES += "ti-codecs-dm355-server"

S = "${WORKDIR}/dm355_codecs_0${PV}"

SRC_URI = "http://tigt_dev.gt.design.ti.com/dev/DVSDK/310_DVSDK/3_10_00/dm355_codecs_${PV}.tar.gz;name=dm355codecsbin \
           file://mapdmaq \
"

do_compile() {
    echo "Do Nothing" 
}

do_install() {

    install -d ${D}/${installdir}/ti-codecs-server
    cd ${S}

    install -m 0755 ${WORKDIR}/mapdmaq ${D}/${installdir}/ti-codecs-server

    install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}

PACKAGES += "ti-codecs-dm355-server"
FILES_ti-codecs-dm355-server = "${installdir}/ti-codecs-server/*"

INSANE_SKIP_ti-codecs-dm355-server = True
