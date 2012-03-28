DESCRIPTION = "Prebuilt V4L2 Plugin for GStreamer"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPL"
HOMEPAGE = "http://www.gstreamer.net/"

SRC_URI = "http://install.source.dir.local/gst-prebuilt-v4l2-${PV}.tar.gz"

SRC_URI[md5sum] = "d661808415afbee67f106fa89431f778"
SRC_URI[sha256sum] = "1ef21b84072fcc346a7e262584430923dd03239ff0cef172b98a222e2fbb0a5f"

INC_PR = "r1"

DEPENDS = "gst-plugins-good"

do_install() {
    install -d ${D}/${libdir}/gstreamer-0.10
    install ${S}/libgstvideo4linux2.so ${D}/${libdir}/gstreamer-0.10
}

FILES_${PN}     += "${libdir}/gstreamer-0.10/*.so"
INSANE_SKIP_${PN} = True
