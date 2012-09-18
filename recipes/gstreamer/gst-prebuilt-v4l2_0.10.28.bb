DESCRIPTION = "Prebuilt V4L2 Plugin for GStreamer"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPL"
HOMEPAGE = "http://www.gstreamer.net/"

SRC_URI = "http://install.source.dir.local/gst-prebuilt-v4l2-${PV}.tar.gz"

SRC_URI[md5sum] = "98cd6cdc510b108d82e9bdfab03572f1"
SRC_URI[sha256sum] = "53ba85cb1bf0ef8481e59b0859162d1647dac7c20ff6484e2aa24a321cadc3e9"

INC_PR = "r2"

DEPENDS = "gst-plugins-good"

do_install() {
    install -d ${D}/${libdir}/gstreamer-0.10
    install ${S}/libgstvideo4linux2.so ${D}/${libdir}/gstreamer-0.10
}

FILES_${PN}     += "${libdir}/gstreamer-0.10/*.so"
INSANE_SKIP_${PN} = True
