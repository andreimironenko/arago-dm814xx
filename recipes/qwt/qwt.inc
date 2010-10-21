DESCRIPTION = "Qt Widget Extension for Technical Applications"
SECTION = "libs"
PRIORITY = "optional"
LICENSE = "GPL"

PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/qwt/qwt-${PV}.tar.bz2;name=qwt"

inherit qt4e

# EXTRA_QMAKEVARS_POST += "LIBS-=qpe"

do_stage() {
	oe_libinstall -so -C lib libqwt ${STAGING_LIBDIR}
        install -m 0644 src/*.h ${STAGING_INCDIR}
}

do_install() {
	install -d ${D}${libdir} \
		   ${D}${includedir}
	oe_libinstall -so -C lib libqwt ${D}${libdir}
        install -m 0644 src/*.h ${D}${includedir}
}

FILES_${PN} = "${libdir}"
FILES_${PN}-dev = "${libdir}/libqwt.so.5.2.1 ${includedir}"
INSANE_SKIP_${PN}-dev = True
