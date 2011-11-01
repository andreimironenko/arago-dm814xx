BRANCH = "03.21.00.03"

require u-boot-davinci.inc

COMPATIBLE_MACHINE = "am180x-evm"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.12_DAVINCIPSP_03.21.00.04"

UVER = "2010.12"
PSPREL = "03.21.00.04.sdk"

# For the am180x we want to enable all the memory on the EVM, but for other
# devices that use the DSP we do not want this change because that memory
# is used for the DSP
SRC_URI += " \
        file://0001-da850evm-change-default-memory-to-not-limit-at-32MB.patch \
"

PR = "r0"
