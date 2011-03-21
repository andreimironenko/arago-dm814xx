require gstreamer-ti.inc

PV = "svnr${SRCREV}"

S = "${WORKDIR}/gstreamer_ti/ti_build/ticodecplugin"

SRCREV = "919"

# apply patches from tracker 1208 to get zero copy support.
# https://gstreamer.ti.com/gf/project/gstreamer_ti/tracker/?action=TrackerItemEdit&tracker_item_id=1208&start=175

SRC_URI = "svn://gforge.ti.com/svn/gstreamer_ti/trunk;module=gstreamer_ti;proto=https;user=anonymous;pswd='' \
"

# apply omapdmaifbsink patch on omap3 platform
# NOTE: this patch need's X11 header/libs
#SRC_URI_append_omap3 = "file://0001-add-omapdmaifbsink.patch"

