DESCRIPTION = "TI Codecs for DM355"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent"
SECTION = "multimedia"
LICENSE = "TI"

# TODO :: Move to common .inc

PV = "03_10_00_02"

SRC_URI[dm355codecsbin.md5sum] = "462b5d0ce7f2648f66adc5d6ae57e507"
SRC_URI[dm355codecsbin.sha256sum] = "257f5f93b0af6c9f942755b97c01014e19cb40ce52dea1c65c58a9bd973293c8"

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
