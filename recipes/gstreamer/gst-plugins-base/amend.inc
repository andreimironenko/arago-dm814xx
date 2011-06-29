# Arago-specific amendments to the standard gst-plugins-ugly recipe to remove
# dependencies
DEPENDS := "${@oe_filter_out('libcdio', bb.data.getVar('DEPENDS', d, 1), d)}"

PR_append = "-arago1"
