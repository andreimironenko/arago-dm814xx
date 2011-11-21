# Arago-specific amendments to the standard OE gst-plugins-good recipes

# Filter out libcdio since it is GPLv3
DEPENDS := "${@oe_filter_out('libcdio', bb.data.getVar('DEPENDS', d, 1), d)}"

# Set the suffix to specify amended packages
PR_append = "-arago2"

SRC_URI = "http://gstreamer.freedesktop.org/src/${PN}/${PN}-${PV}.tar.bz2;name=archive \
           file://0001-v4l2-fix-handling-of-RGB32-BGR32-formats.patch \
           file://0002-v4l2sink-Add-rotation-support.patch \
           file://0003-v4l2sink-Add-flip-property.patch \
           file://0004-v4l2sink-Add-support-for-omap24xxvout-driver.patch \
           file://0005-v4l2sink-Add-support-for-omap_vout-driver.patch \
           file://0006-v4l2-increase-v4l2sink-element-rank.patch \
           file://0007-use-GstQueryBuffers-to-get-buffer-requirements.patch \
           file://0008-add-rowstride-support.patch \
           file://0009-use-GstEventCrop-to-get-crop-info.patch \
           file://0010-v4l2-prefer-NV12.patch \
           file://0011-v4l2sink-fix-issue-seen-with-autoconvert.patch \
           file://0012-v4l2sink-Add-Userptr-support.patch \
           file://0013-v4l2sink-interlaced-seq-tb-format.patch \
"
