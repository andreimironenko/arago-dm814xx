DESCRIPTION = "DM365 Codecs"
SECTION = "multimedia"
LICENSE = "TI"

PV = "03_10_00_03"

SRC_URI = "ftp://gtftp01.gt.design.ti.com/arago/dm365_codecs_${PV}.tar.gz;name=dm365codecs \
           file://mapdmaq"

SRC_URI[dm365codecs.md5sum] = "502c11dd2bf22e93e308bb0cae71f701"
SRC_URI[dm365codecs.sha256sum] = "fb1a591060baf2a75b200a763e72d9f1c9ff5a2853a0dbfa928eb109847514a5"

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
