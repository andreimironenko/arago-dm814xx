require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti811x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI811XPSP_ENG4_REL"

UVER = "2010.06"
PSPREL = "04.07.00.01"

UBOOT_MAKE_TARGET = "u-boot.ti"

INC_PR = "r1"

SRC_URI += "file://0001-drivers-net-cpsw-halt-cpsw-properly-to-stop-receivin.patch \
            file://ti811x-u-boot-nand-change-to-hamming-ecc-scheme.patch \
"
