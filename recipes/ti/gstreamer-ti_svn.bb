require gstreamer-ti.inc

PV = "svnr${SRCREV}"

S = "${WORKDIR}/gstreamer_ti/ti_build/ticodecplugin"

SRCREV = "612"

SRC_URI = "svn://gforge.ti.com/svn/gstreamer_ti/trunk;module=gstreamer_ti;proto=https;user=anonymous;pswd='' \
  file://gstreamer-ti-tracker-1055.patch;patch=1 \
  file://gstreamer-ti-rc.sh \
  file://gst-ti.sh \
"

SRC_URI_append_dm365 = "file://loadmodules.sh"

SRC_URI_append_omapl137 = " \
 file://gstreamer-ti-omapl137.patch;patch=1 \
 file://loadmodules.sh \
"

SRC_URI_append_omapl138 = " \
 file://gstreamer-ti-omapl138.patch;patch=1 \
 file://loadmodules.sh \
"

SRC_URI_append_dm6467 = "file://gstreamer-ti-dm6467-usesinglecsserver.patch;patch=1"

