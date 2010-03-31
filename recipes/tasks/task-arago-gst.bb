DESCRIPTION = "Task to add base gstreamer and TI plugins"
PR = "r14"

inherit task

RDEPENDS_${PN} = " \
    gstreamer \
    gst-plugins-base \
    gst-plugins-good \
    gst-plugins-bad \
    gst-plugins-ugly \
    gst-plugins-base-meta \
    gst-plugins-good-meta \
    gst-plugins-bad-meta \
    gst-plugins-ugly-meta \
    "
