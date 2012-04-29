DESCRIPTION = "Prebuilt V4L2 Plugin for GStreamer"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPL"
HOMEPAGE = "http://www.gstreamer.net/"

SRC_URI = "http://install.source.dir.local/gst-prebuilt-v4l2-${PV}.tar.gz"

SRC_URI[md5sum] = "44552435b398dfdfdebb0af88ca58b6b"
SRC_URI[sha256sum] = "93369dbb98b55acd43e6a3bffa7e7d3d17e9d0cb8b72b841a942758d8b5ee6fc"

INC_PR = "r2"

DEPENDS = "gst-plugins-good"

do_install() {
    install -d ${D}/${libdir}/gstreamer-0.10
    install ${S}/libgstvideo4linux2.so ${D}/${libdir}/gstreamer-0.10
}

FILES_${PN}     += "${libdir}/gstreamer-0.10/*.so"
INSANE_SKIP_${PN} = True
