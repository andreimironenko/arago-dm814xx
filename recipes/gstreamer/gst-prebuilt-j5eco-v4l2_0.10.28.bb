DESCRIPTION = "Prebuilt J5Eco specific V4L2 Plugin for GStreamer"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPL"
HOMEPAGE = "http://www.gstreamer.net/"

SRC_URI = "http://install.source.dir.local/gst-prebuilt-j5eco-v4l2-${PV}.tar.gz"

SRC_URI[md5sum] = "21e8200791a50b835b738ddad9028b88"
SRC_URI[sha256sum] = "f363a489b393840601c8a04c0f0548ad4bbc26f1666bebeb8d62199cf7b285d6"

INC_PR = "r1"

DEPENDS = "gst-plugins-good"

do_install() {
    install -d ${D}/${libdir}/gstreamer-0.10
    install ${S}/libgstvideo4linux2.so ${D}/${libdir}/gstreamer-0.10
}

FILES_${PN}     += "${libdir}/gstreamer-0.10/*.so"
INSANE_SKIP_${PN} = True
