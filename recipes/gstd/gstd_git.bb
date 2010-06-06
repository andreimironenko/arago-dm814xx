DESCRIPTION = "gstd: a Gstreamer-based streaming server"
HOMEPAGE = "http://sourceforge.net/projects/harrier/"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

inherit autotools pkgconfig

DEPENDS = "dbus dbus-glib gstreamer readline"
RDEPENDS_${PN} = "dbus dbus-glib gstreamer gst-plugins-base"
RRECOMENDS_${PN} = "gstreamer-ti"

SRCREV = "5812b0c97f45d0d11eb9ba36ffc4209a07277fe6"

PV = "1.0"
PR = "r14"
PR_append = "+gitr${SRCREV}"

SRC_URI = "git://gstd.git.sourceforge.net/gitroot/gstd/gstd;protocol=git \
           "
S = "${WORKDIR}/git"

# We don't want to run autoconf or automake, unless you have 
# automake > 1.11 with vala support
do_configure() {
    oe_runconf
}

FILES_${PN} += "${datadir}/dbus-1/*/*.service"
FILES_${PN}-dev += "${datadir}/dbus-1/interfaces/*.xml"
