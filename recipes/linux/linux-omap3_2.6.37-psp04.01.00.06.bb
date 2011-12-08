require linux-omap3.inc

COMPATIBLE_MACHINE = "ti814x"

KVER = "2.6.37"
PSPREL = "04.01.00.06.patch2"

SRCREV = "v2.6.37_TI814XPSP_04.01.00.06.patch2"

SRC_URI += "file://0001-ti814x-added-code-for-disabling-the-least-significan.patch \
"
