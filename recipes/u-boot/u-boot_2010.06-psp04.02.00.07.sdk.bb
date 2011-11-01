require u-boot-omap3.inc

COMPATIBLE_MACHINE = "am37x-evm|am3517-evm"

BRANCH = "master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_OMAPPSP_04.02.00.07"

UVER = "2010.06"
PSPREL = "04.02.00.07.sdk"

SRC_URI += " \
file://0001-omap3_evm-Added-function-calls-to-set-volts-speed-on.patch \
file://0002-omap3_evm-Changed-default-clock-rate-for-AM3517.patch \
file://0003-Set-ethaddr-environment-variable-and-program-the-EMA.patch \
"

SRC_URI_append_am37x-evm = " \
file://0001-omap3-evm-allocate-VRFB-buffer-in-bootargs-to-enable.patch \
"

PR = "r3"
