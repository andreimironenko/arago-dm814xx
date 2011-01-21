require u-boot-omap3.inc

COMPATIBLE_MACHINE = "am37x-evm|am3517-evm|dm37x-evm"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "c0a8fb217fdca7888d89f9a3dee74a4cec86562"

UVER = "2009.11"
PSPREL = "03.00.01.06.sdk"

# Add ECC support patches
SRC_URI += "file://0001-Uboot-BCH-4b8b-Error-Correction.patch"

PR = "r0"
