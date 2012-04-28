# Arago-specific amendments to the standard OE gstreamer recipes

# Set the suffix to specify amended packages
PR_append = "-arago2"

SRC_URI = "http://gstreamer.freedesktop.org/src/gstreamer/gstreamer-0.10.32.tar.bz2;name=archive \
           file://0001-gst-launch-add-loop-argument.patch \
           file://0003-add-GstQueryBuffers-query.patch \
           file://0004-Add-GstEventCrop-event.patch \
           file://0005-basetransform-don-t-do-unnecessary-pad_alloc.patch \
           file://0006-queue-free-erroneous-buffer.patch \
"
