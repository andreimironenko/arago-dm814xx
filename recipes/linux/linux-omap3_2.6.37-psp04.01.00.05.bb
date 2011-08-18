require linux-omap3.inc

COMPATIBLE_MACHINE = "ti814x"

KVER = "2.6.37"
PSPREL = "04.01.00.05.patch1"

BRANCH = "ti81xx-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2.6.37_TI814XPSP_04.01.00.05.patch1"

SRC_URI += "file://0001-TI814x-Set-the-required-bit-for-TI814x-HDMI.patch \
            file://0001-ti814x-Force-Enable-I2C-Clock.patch \
"
