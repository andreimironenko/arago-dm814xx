DESCRIPTION = "Matrix GUI"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrix_gui/"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

SRCREV = "57"
PV = "1.0"
PR = "r6+svnr${SRCPV}"

PLATFORM ?= "dm3730-am3517-evm"
PLATFORM_omapl138-evm = "omapl138"

# Skip the QA for browser binary (temporary)
INSANE_SKIP_${PN} = "True"

SRC_URI = "svn://gforge.ti.com/svn/matrix_gui/;module=trunk;proto=https;user=anonymous;pswd=''"

S = "${WORKDIR}/trunk"

INITSCRIPT_NAME = "matrix-gui"
INITSCRIPT_PARAMS = "defaults 99"

inherit qt4e update-rc.d

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/matrix_gui ${D}/${bindir}
	for i in ${MATRIX_EXTRA_BINS}; do
		install -m 0755 ${S}/bin/${i} ${D}/${bindir}
	done
	install -d ${D}/${datadir}/matrix/html
	cp -ar ${S}/${PLATFORM}/*.html ${D}/${datadir}/matrix/
	install -d ${D}/${datadir}/matrix/images
	install -m 0644 ${S}/images/*.png ${D}/${datadir}/matrix/images/
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 0755 ${S}/${PLATFORM}/etc/init ${D}${sysconfdir}/init.d/matrix-gui
}

RRECOMMENDS_${PN} = "qt4-embedded-plugin-mousedriver-tslib"
FILES_${PN} += "${datadir}/matrix/*"
