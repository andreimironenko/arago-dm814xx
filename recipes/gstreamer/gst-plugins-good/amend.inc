# Arago-specific amendments to the standard OE gst-plugins-good recipes

# Filter out libcdio since it is GPLv3
DEPENDS := "${@oe_filter_out('libcdio', bb.data.getVar('DEPENDS', d, 1), d)}"

# Set the suffix to specify amended packages
PR_append = "-arago1"
