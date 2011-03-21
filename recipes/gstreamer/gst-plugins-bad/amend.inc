# Arago-specific amendments to the standard OE gst-plugins-bad recipes

# Remove OpenCV for now, as it doesn't build with CSL 2009q1
DEPENDS := "${@oe_filter_out('opencv', bb.data.getVar('DEPENDS', d, 1), d)}"

# Set the suffix to specify amended packages
PR_append = "-arago2"
