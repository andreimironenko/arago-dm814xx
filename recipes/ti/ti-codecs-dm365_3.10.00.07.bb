DESCRIPTION = "DM365 Codecs"
SECTION = "multimedia"
LICENSE = "TI"

PV = "03_10_00_07"

SRC_URI = "http://tigt_dev.gt.design.ti.com/dev/DVSDK/310_DVSDK/3_10_00/dm365_codecs_${PV}.tar.gz;name=dm365codecs \
           file://mapdmaq"

SRC_URI[dm365codecs.md5sum] = "6d9148b5824f047129048a28557fb40f"
SRC_URI[dm365codecs.sha256sum] = "4fe6951fd05fbdd1ccff8b424e02e0feb176e20a8c337db036b1f864ff191170"

S = "${WORKDIR}/dm365_codecs_${PV}"

PROVIDES += "ti-codecs-dm365-server"

require ti-paths.inc
require ti-staging.inc

do_compile() {
	echo "Do nothing"
}

do_install () {
	install -d ${D}/${installdir}/ti-codecs-server
	cd ${S}

	install -m 0755 ${WORKDIR}/mapdmaq ${D}/${installdir}/ti-codecs-server

	install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
	cp -pPrf ${S}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}

PACKAGES += "ti-codecs-dm365-server"
FILES_ti-codecs-dm365-server = "${installdir}/ti-codecs-server/*"

INSANE_SKIP_ti-codecs-dm365-server = True
