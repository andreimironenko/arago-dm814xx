require linux-omap3.inc

KVER = "2.6.32"
PSPREL = "03.00.01.06.sdk"

COMPATIBLE_MACHINE = "am37x-evm"

SRCREV = "v2.6.32_OMAPPSP_03.00.01.06.patch1"


SRC_URI += "file://0001-linux-omap3-PSP-3.0.1.6-kernel-with-OCF-Linux.patch"
