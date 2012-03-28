DESCRIPTION = "Prebuilt V4L2 Plugin for GStreamer"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPL"
HOMEPAGE = "http://www.gstreamer.net/"

SRC_URI = "http://install.source.dir.local/gst-prebuilt-v4l2-${PV}.tar.gz"

SRC_URI[md5sum] = "97ccd1c065e4ef86a549ae19d0176fd7"
SRC_URI[sha256sum] = "3b23393e2c12b3882e6e2e2fbbf5f19301f8c484860a6fc1159452b6da8d60c8"

INC_PR = "r1"

DEPENDS = "gst-plugins-good"

do_install() {
    install -d ${D}/${libdir}/gstreamer-0.10
    install ${S}/libgstvideo4linux2.so ${D}/${libdir}/gstreamer-0.10
}

FILES_${PN}     += "${libdir}/gstreamer-0.10/*.so"
INSANE_SKIP_${PN} = True
