require linux-omap3.inc

COMPATIBLE_MACHINE = "ti816x|ti814x|ti811x"

KVER = "2.6.37"
PSPREL = "04.04.00.01"

BRANCH = "ti81xx-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "TI81XXPSP_04.04.00.01"

SRC_URI_append_ti814x = " \
            file://0001-ti814x-added-code-for-disabling-the-least-significan.patch \
"
