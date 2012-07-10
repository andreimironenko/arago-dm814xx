DESCRIPTION = "Prebuilt J5Eco specific V4L2 Plugin for GStreamer"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPL"
HOMEPAGE = "http://www.gstreamer.net/"

SRC_URI = "http://install.source.dir.local/gst-prebuilt-j5eco-v4l2-${PV}.tar.gz"

SRC_URI[md5sum] = "352828c1cd887a592d26a6c00dd92903"
SRC_URI[sha256sum] = "7f6b3e1fe1722a0983ff565c938433a639bcc230105f4779672b29a8de45635d"

INC_PR = "r2"

DEPENDS = "gst-plugins-good"

do_install() {
    install -d ${D}/${libdir}/gstreamer-0.10
    install ${S}/libgstvideo4linux2.so ${D}/${libdir}/gstreamer-0.10
    install ${S}/libgstomx.so ${D}/${libdir}/gstreamer-0.10
}

FILES_${PN}     += "${libdir}/gstreamer-0.10/*.so"
INSANE_SKIP_${PN} = True
