require linux-omap3.inc

KVER = "2.6.32"
PSPREL = "03.00.01.06.sdk"

COMPATIBLE_MACHINE = "am37x-evm|am3517-evm|dm37x-evm"

SRCREV = "v2.6.32_OMAPPSP_03.00.01.06.patch1"

# This variable contains all the common PSP approved patches used by
# the am37x-evm and dm37x-evm machine types.
#
# Append OCF support (PSP approved)
# Append 720P DVI color fix support (PSP approved)
# Append SmartReflex support (PSP approved)
PSP_PATCHES = " \
    file://0001-linux-omap3-PSP-3.0.1.6-kernel-with-OCF-Linux.patch \
    file://0001-OMAP-DSS-Invert-pixel-clock-for-DVI-output.patch \
    file://0001-OMAP3-PM-Introduce-Smartreflex-support-on-OMAP3630.patch \
    file://0002-OMAP3630-PM-implement-Foward-Body-Bias-for-OPP1G.patch \
    file://0003-AM-DM3730-Change-default-VDD1-voltage-for-OPP1G-to.patch \
    file://0004-AM-DM3730-SR-Add-additional-voltage-margin-to-OPP.patch \
    "

SRC_URI_append_am37x-evm = "${PSP_PATCHES}"
SRC_URI_append_dm37x-evm = "${PSP_PATCHES}"

# Generic append to add ECC fix for all supported devices
SRC_URI_append = " file://0001-Kernel-BCH-4b8b-Error-Correction.patch"
