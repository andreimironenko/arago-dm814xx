# Arago-specific amendments to the standard OE Qt native tools recipes

EXTRA_OECONF := "${@oe_filter_out('-embedded', bb.data.getVar('EXTRA_OECONF', d, 1), d)}"

# Qt/E 4.7.x from Nokia is dual-licensed (LGPLv2.1 or GPLv3)
# For our distribution purposes we only use LGPLv2.1
LICENSE = "LGPLv2.1"

# Set the suffix to specify amended packages
PR_append = "-arago4"
