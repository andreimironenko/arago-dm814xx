require u-boot-omapl1.inc

UBOOT_MACHINE_da850-omapl138-evm = "da850_omapl138_evm_config"

COMPATIBLE_MACHINE = "(omapl137|da850-omapl138-evm|am180x-evm)"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2009.11_DAVINCIPSP_03.20.00.12"

UVER = "2009.11"
PSPREL = "03.20.00.12"

PR = "r1"
