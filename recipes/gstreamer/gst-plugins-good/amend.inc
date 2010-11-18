# Make the recipe machine dependent - mainly because v4l2src element has tight depedency with kernel header
MULTIMACH_ARCH = ${MACHINE_ARCH} 
DEPENDS_append = " virtual/kernel"

SRC_URI += "\ 
file://0001-v4l2src-add-input-src-property-to-specify-capture-in.patch \
file://0002-v4l2src-keep-track-of-the-input-ID-that-will-be-used.patch \
file://0003-v4l2src-make-sure-capture-buffer-size-is-aligned-on-.patch \
file://0004-osssink-handle-all-supported-sample-rates.patch \
file://0005-v4l2src-add-support-for-DaVinci-platforms-using-MVL-.patch \
file://0006-v4l2src-support-NV12-capture-on-DM365-using-the-IPIP.patch \
file://0007-v4l2src-accept-EPERM-as-a-non-fatal-error-for-VIDIOC.patch \
file://0008-v4l2src-try-progressive-mode-first-for-component-inp.patch \
file://0009-v4l2src-add-support-for-NV16-colorspace.patch \
file://0010-v4l2src-set-bytesperline-and-sizeimage-before-callin.patch \
file://0011-v4l2src-update-gst_v4l2_get_norm-to-handle-DM6467T-a.patch \
file://0012-v4l2src-add-V4L2-ioctl-calls-to-initialize-capture-d.patch \
file://0013-v4l2src-disable-video-device-polling-by-default-on-D.patch \
file://0014-v4l2src-Skip-detection-of-private-controls.patch \
file://0015-v4l2src-Remove-short-circuit-of-VIDIOC_S_FMT-call.patch \
file://0016-v4l2src-add-feature-to-artifically-lower-framerate-b.patch \
file://0017-v4l2src-always-set-the-desired-framerate-on-DM365.patch \
"

# pass machine specific flag needed by v4l2src elements.
CPPFLAGS_dm6467_append = " -DPlatform_dm6467 -I${STAGING_KERNEL_DIR}/include"
CPPFLAGS_dm365_append = " -DPlatform_dm365 -I${STAGING_KERNEL_DIR}/include"
CPPFLAGS_dm355_append = " -DPlatform_dm355 -I${STAGING_KERNEL_DIR}/include"
CPPFLAGS_dm6467t_append = " -DPlatform_dm6467t -I${STAGING_KERNEL_DIR}/include"
CPPFLAGS_omapl138_append = " -DPlatform_omapl138"

# Arago-specific amendments to the standard gst-plugins-good recipe to remove
# dependencies
DEPENDS := "${@oe_filter_out('libcdio', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('wavpack', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libvisual', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('jack', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('virtual/libsdl', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libvpx', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('x264', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libmms', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libmusicbrainz', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('schroedinger', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('tremor', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('cdparanoia', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('pango', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('pango', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('virtual/libx11', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('gnome-vfs', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libxv', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('gtk+', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libsm', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libvorbis', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libtheora', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libogg', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('hal', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('pulseaudio', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('speex', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('libsoup-2.4', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('flac', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('openssl', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('esound', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS := "${@oe_filter_out('gconf', bb.data.getVar('DEPENDS', d, 1), d)}"

# bump PR
PR_append = "-arago3"


