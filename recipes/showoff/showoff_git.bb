DESCRIPTION = "ShowOff: a QT based demo for EVMs"
HOMEPAGE = "http://sourceforge.net/projects/showoff"
MAINTAINER = "http://sourceforge.net/projects/showoff"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

DEPENDS = "dbus gstd"
RDEPENDS_${PN} = "dbus alsa-utils-amixer gst-ipcsink gstd"
RRECOMMENDS_${PN} = "qt4-embedded-plugin-mousedriver-tslib gstreamer-ti"

SRCREV=e907feb5b484000914690225e9502f10c3c8b442

PV = "1.0"
PR = "r23"
PR_append= "+gitr${SRCREV}"

SRC_URI = "git://showoff.git.sourceforge.net/gitroot/showoff/showoff;protocol=git \
	file://0001-set-RTCodecThread-FALSE-and-use-ffmpegcolorspace-unt.patch;patch=1\
           file://showoff \
           file://showoff.init \
           "
S = "${WORKDIR}/git"

INITSCRIPT_NAME = "showoff"
INITSCRIPT_PARAMS = "defaults 99"

inherit qt4e update-rc.d

do_install() {
    install -d ${D}/${libexecdir}
    install -d ${D}/${bindir}
    install -m 0755  ${S}/ShowOff ${D}/${libexecdir}
    install -m 0755  ${WORKDIR}/showoff ${D}/${bindir}
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/showoff.init ${D}${sysconfdir}/init.d/showoff
}

pkg_postinst_${PN} () {
    # Only run on the target board
    if [ x"$D" = "x" ]; then
        # Check if the gstreamer register is build already, otherwise build it
        if [ ! -f /home/root/.gstreamer-0.10/registry.arm.bin ] ; then
            echo -n "\nBuilding Gstreamer Register... "
            gst-inspect >/dev/null 2>&1
            echo "done."
        fi
    fi
}
