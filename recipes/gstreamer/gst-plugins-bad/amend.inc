# Arago-specific amendments to the standard OE gst-plugins-bad recipes

# New versions depend on librsvg
# Fixed in OE, amend locally until arago-oe-dev snapshot is updated
DEPENDS += "librsvg"

# Set the suffix to specify amended packages
PR_append = "-arago1"
