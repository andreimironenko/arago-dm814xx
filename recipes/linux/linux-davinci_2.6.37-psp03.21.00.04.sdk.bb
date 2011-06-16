require linux-davinci.inc

BRANCH = "03.21.00.03"

SRCREV = "v2.6.37_DAVINCIPSP_03.21.00.04"

COMPATIBLE_MACHINE = "(dm365-evm|dm368-evm|omapl138)"

KVER = "2.6.37"
PSPREL = "03.21.00.04.sdk"
SRC_URI += " \
	file://0001-TI-WL12xx-MMC-patches-03.21.00.04.patch \
	file://0002-da850-Set-maximum-OPP-frequency-to-456MHz.patch \
	file://0003-AM18xx-WL1271-Enable-BT.patch \
	file://0004-PSP03.21.00.04.sdk-activate-wireless-extensions.patch \
	file://0005-Davinci-da850-Add-Mistral-WL12XX-config-support-to.patch \
	"

SRC_URI_append_am180x-evm = " file://uio_pruss.patch"

SRC_URI_append_da850-omapl138-evm = " file://uio_pruss.patch"
