DESCRIPTION = "DM365 Codecs"
SECTION = "multimedia"
LICENSE = "TI"

PV = "03_10_00_05"

SRC_URI = "ftp://gtftp01.gt.design.ti.com/arago/dm365_codecs_${PV}.tar.gz;name=dm365codecs \
           file://mapdmaq"

SRC_URI[dm365codecs.md5sum] = "f5c34de13f1942a73ef258f2cc95313b"
SRC_URI[dm365codecs.sha256sum] = "9441c39ea6aa17c942b9bc827a0a102da26efd2bc9d2b4fea82e5a2058e2e822"

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
