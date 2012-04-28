# Arago-specific amendments to the standard OE gst-plugins-bad recipes

# Remove OpenCV for now, as it doesn't build with CSL 2009q1
DEPENDS := "${@oe_filter_out('opencv', bb.data.getVar('DEPENDS', d, 1), d)}"

# Filter out libcdio since it is GPLv3
DEPENDS := "${@oe_filter_out('libcdio', bb.data.getVar('DEPENDS', d, 1), d)}"

# Set the suffix to specify amended packages
PR_append = "-arago5"

EXTRA_OECONF += "--disable-vp8 \
"

# file://0004-h264parse-Add-workaround-for-separate-codec_config-a.patch \

SRC_URI = "http://gstreamer.freedesktop.org/src/${PN}/${PN}-${PV}.tar.bz2;name=archive \
           file://0001-freeze-Add-timeout-property.patch \
           file://0002-fixes-to-mux-h264-stream-utilizing-ctts.patch \
           file://0003-ugly-hack-to-avoid-a-memcpy.patch \
           file://0005-jpegparse-ignore-unhandled-application-markers.patch \
           file://0006-mpegvideoparse-merge-sequence-header.patch \
           file://0007-h264parse-fix-boundary-condition.patch \
           file://0008-h264parse-modifications-for-playbin.patch \
           file://0010-mpegtsdemux-picking-up-a-fix-to-genrate-no-more-pads.patch \
"
