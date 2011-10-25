# Arago-specific amendments to the standard OE oprofile recipes

# Be more specific with license version, instead of generic GPL
LICENSE = "GPLv2"

# We only need binutils libraries and headers for oprofile
# Filter out binaries and symlinks packages
DEPENDS := "${@oe_filter_out('binutils', bb.data.getVar('DEPENDS', d, 1), d)}"
DEPENDS += "binutils-dev"
DEPENDS += "zlib"

RDEPENDS_oprofile = ""

# Set the suffix to specify amended packages
PR_append = "-arago1"
