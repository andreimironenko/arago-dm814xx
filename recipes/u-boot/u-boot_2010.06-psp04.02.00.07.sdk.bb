require u-boot-omap3.inc

COMPATIBLE_MACHINE = "am37x-evm|am3517-evm"

BRANCH = "master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_OMAPPSP_04.02.00.07"

UVER = "2010.06"
PSPREL = "04.02.00.07.sdk"

# Add uEnv.txt boot support patches
SRC_URI += " \
file://0001-omap3_evm-enable-use-of-plain-text-file-uEnv.txt-ins.patch \
file://0001-omap3_evm-Added-function-calls-to-set-volts-speed-on.patch \
file://0002-omap3_evm-Changed-default-clock-rate-for-AM3517.patch \
file://0003-Set-ethaddr-environment-variable-and-program-the-EMA.patch \
"

PR = "r2"
