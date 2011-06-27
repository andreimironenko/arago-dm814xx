require linux-omap3.inc

KVER = "2.6.37"
PSPREL = "04.02.00.07.sdk"

SRCREV= "v2.6.37_OMAPPSP_04.02.00.07"

COMPATIBLE_MACHINE = "am37x-evm"

# The following patches below have been upstreamed to linux kernel 2.6.39 tree
# and will not be needed once we move to this kernel.
# The only exception is "0002-allow-selecting-WL12XX_PLATFROM_DATA-independently.patch"
# which is needed for building against a compat-wireless package

SRC_URI += " \
    file://0001-linux-omap3-Add-OCF-support-to-2.6.37-kernel.patch \
    file://0001-omap3evm-add-support-for-the-WL12xx-WLAN-module.patch \
    file://0002-allow-selecting-WL12XX_PLATFROM_DATA-independently.patch \
    file://0003-mmc-skip-detection-of-nonremovable-cards-on-rescan.patch \
    file://0004-mmc-sdio-don-t-reinitialize-nonremovable-powered-res.patch \
    file://0005-mmc-sdio-don-t-power-up-cards-on-system-suspend.patch \
    file://0006-wl12xx-Backport-wl12xx-platform-data.patch \
    file://0007-activate-wireless-extensions.patch \
"

# Update the kernel so support suspend/resume when the file system is located
# on the SD card.  This is also being submitted to the PSP team for inclusion
# in future releases.
SRC_URI += " \
    file://0001-Fix-matrix-suspend.patch \
"

# Update the OCF kernel patch to remove non-existing components for
# non-TI hardware.  This allows the make clean target to work.
# Update the defconfig for omap3 to enable OCF and WLAN by default
SRC_URI += " \
    file://0001-OCF-support-remove-support-for-non-TI-hardware.patch \
    file://0002-omap3_evm_defconfig-add-OCF-driver-support.patch \
    file://0003-omap3_evm_defconfig-add-WLAN-config-options.patch \
"
