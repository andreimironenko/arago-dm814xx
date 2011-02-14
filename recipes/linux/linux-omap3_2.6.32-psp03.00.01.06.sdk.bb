require linux-omap3.inc

KVER = "2.6.32"
PSPREL = "03.00.01.06.sdk"

COMPATIBLE_MACHINE = "am37x-evm|am3517-evm|dm37x-evm"

SRCREV = "v2.6.32_OMAPPSP_03.00.01.06.patch1"

# Append OCF support (PSP approved)
# Append 720P DVI color fix support (PSP approved)
SRC_URI_append_am37x-evm = " \
    file://0001-linux-omap3-PSP-3.0.1.6-kernel-with-OCF-Linux.patch \
    file://0001-OMAP-DSS-Invert-pixel-clock-for-DVI-output.patch \
    "

# Append OCF support (PSP approved)
# Append 720P DVI color fix support (PSP approved)
SRC_URI_append_dm37x-evm = " \
    file://0001-linux-omap3-PSP-3.0.1.6-kernel-with-OCF-Linux.patch \
    file://0001-OMAP-DSS-Invert-pixel-clock-for-DVI-output.patch \
    "

# Generic append to add ECC fix for all supported devices
SRC_URI_append = " file://0001-Kernel-BCH-4b8b-Error-Correction.patch"
