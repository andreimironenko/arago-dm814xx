require u-boot-omap3.inc

COMPATIBLE_MACHINE = "am37x-evm|am3517-evm|dm37x-evm"

BRANCH = "master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_OMAPPSP_04.02.00.07"

UVER = "2010.06"
PSPREL = "04.02.00.07.sdk"

# Add uEnv.txt boot support patches
SRC_URI += "file://0001-omap3_evm-enable-use-of-plain-text-file-uEnv.txt-ins.patch"

PR = "r0"
