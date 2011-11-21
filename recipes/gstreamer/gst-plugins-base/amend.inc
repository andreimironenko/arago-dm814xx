# Arago-specific amendments to the standard gst-plugins-base recipe to remove
# dependencies
DEPENDS := "${@oe_filter_out('libcdio', bb.data.getVar('DEPENDS', d, 1), d)}"
EXTRA_OECONF := "${@oe_filter_out('--enable-nls', bb.data.getVar('EXTRA_OECONF', d, 1), d)}"

PR_append = "-arago2"

SRC_URI = "http://gstreamer.freedesktop.org/src/${PN}/${PN}-${PV}.tar.bz2;name=archive \
           file://0001-add-rowstride-support-to-video-utility-functions.patch \
           file://0002-stridetransform-skeletal-implementation-of-stridetra.patch \
           file://0003-stridetransform-implement-caps-negotiation-and-relat.patch \
           file://0004-stridetransform-implement-transform-function.patch \
           file://0005-add-gst_stride_transform_transform_size.patch \
           file://0006-fix-a-small-typo.-need-to-use-the-smaller-of-new_wid.patch \
           file://0007-Add-NV12-support-in-stridetransform.patch \
           file://0008-add-basic-support-for-I420-NV12-colorspace-conversio.patch \
           file://0009-fix-to-avoid-parsing-caps-on-every-frame.patch \
           file://0010-refactor-stridetransform-to-make-it-easier-to-add-ne.patch \
           file://0011-add-some-neon.patch \
           file://0012-add-support-to-convert-to-YUY2-YUYV-color-format.patch \
           file://0013-Add-support-for-RGB565-to-stridetransform.patch \
           file://0014-stridetransform-updates-for-new-extra-anal-compiler-.patch \
           file://0015-stridetransform-fix-problem-transforming-caps-with-l.patch \
           file://0016-modify-playbin-to-use-stridetransform.patch \
           file://0017-playbin-disable-interlaced-support.patch \
           file://0018-textoverlay-add-stride-support.patch \
           file://0019-video-more-flexible-video-caps-utility.patch \
           file://0020-video-fix-endianess-issue-for-16bit-RGB-formats.patch \
           file://0021-stride-more-flexible-stride-color-conversion.patch \
           file://0022-stride-support-for-32bit-RGB-formats.patch \
           file://0023-ffmpegcolorspace-support-for-rowstride.patch \
           file://0024-discoverer-rowstride-support.patch \
           file://0025-playsink-link-omx_colorconv-to-do-HW-accel-color-con.patch \
           file://0026-playbin-link-omx_scaler-to-do-HW-accel-scale.patch \
"
