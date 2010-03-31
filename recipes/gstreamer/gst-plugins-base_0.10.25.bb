require gst-plugins.inc

SRC_URI += "file://fix-playbin2.patch;patch=1 \
"

PR = "${INC_PR}.3"

PROVIDES += "gst-plugins"

EXTRA_OECONF += "--disable-tests --disable-examples --disable-x --disable-ogg --disable-vorbis --disable-pango --enable-alsa --disable-subparse"

# gst-plugins-base only builds the alsa plugin
# if alsa has been built and is present.  You will
# not get an error if this is not present, just 
# a missing alsa plugin
DEPENDS += "alsa-lib"
