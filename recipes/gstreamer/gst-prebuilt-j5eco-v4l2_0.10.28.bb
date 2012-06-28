DESCRIPTION = "Prebuilt J5Eco specific V4L2 Plugin for GStreamer"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPL"
HOMEPAGE = "http://www.gstreamer.net/"

SRC_URI = "http://install.source.dir.local/gst-prebuilt-j5eco-v4l2-${PV}.tar.gz"

SRC_URI[md5sum] = "e3eeb894fe0a3f5eb7c1d6b447c7e71b"
SRC_URI[sha256sum] = "2b1fa1966a9674e310382ae6a3fc1b33428cdbb83104a341a15ca240e401cc33"

INC_PR = "r2"

DEPENDS = "gst-plugins-good"

do_install() {
    install -d ${D}/${libdir}/gstreamer-0.10
    install ${S}/libgstvideo4linux2.so ${D}/${libdir}/gstreamer-0.10
    install ${S}/libgstomx.so ${D}/${libdir}/gstreamer-0.10
}

FILES_${PN}     += "${libdir}/gstreamer-0.10/*.so"
INSANE_SKIP_${PN} = True
