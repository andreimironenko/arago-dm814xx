require linux-omap3.inc

COMPATIBLE_MACHINE = "ti816x|ti814x|ti811x"

KVER = "2.6.37"
PSPREL = "04.04.00.01"

BRANCH = "ti81xx-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2.6.37_TI81XXPSP_04.04.00.01.patch2"

# The following patches add support of Wl12xx for ti814x-evm
SRC_URI += " \
	file://0002-mmc-skip-detection-of-nonremovable-cards-on-rescan.patch \
	file://0003-mmc-sdio-don-t-reinitialize-nonremovable-powered-res.patch \
	file://0004-mmc-sdio-don-t-power-up-cards-on-system-suspend.patch \
	file://0005-mmc-fix-division-by-zero-in-MMC-core.patch \
	file://0006-wl12xx-Backport-wl12xx-platform-data.patch \
	file://0007-wl12xx-Fix-out-of-tree-build-issues.patch \
	file://0008-ARM-ti81xx-Fix-ti8148-s-mmc-indices.patch \
	file://0009-omap_hsmmc-Work-around-lack-of-mmc0-card-detection-p.patch \
	file://0010-arm-ti8148evm-Add-wl12xx-wlan-support.patch \
	file://0011-PATCH-ti8148evm-fix-of-mmc-array.patch \
"

SRC_URI_append_ti814x = " \
    file://0001-ti814x-added-code-for-disabling-the-least-significan.patch \
"

SRC_URI_append_ti811x = " \
    file://0001-ti814x-added-code-for-disabling-the-least-significan.patch \
