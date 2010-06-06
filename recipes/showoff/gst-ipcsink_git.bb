DESCRIPTION = "gst-ipcsink: custom gstreamer element for IPC video sink"
HOMEPAGE = "http://sourceforge.net/projects/showoff"
MAINTAINER = "http://sourceforge.net/projects/showoff"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

DEPENDS = "gstreamer gst-plugins-base"

SRCREV=e379f6a0ecbd6d4aa57f821d3d8d55a8f1231551

PV = "1.0"
PR = "r2"
PR_append= "+gitr${SRCREV}"

SRC_URI = "git://showoff.git.sourceforge.net/gitroot/showoff/gst-ipcsink;protocol=git"
S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/gstreamer-0.10/*.so ${sysconfdir}"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.a ${libdir}/gstreamer-0.10/*.la"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"

inherit autotools

