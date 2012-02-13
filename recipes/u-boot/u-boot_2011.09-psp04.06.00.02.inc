require u-boot_2011.09.inc

COMPATIBLE_MACHINE = "ti33x"

BRANCH = "int_am335xpsp_04.06.00.01-v2011.09-for-sdk-05.03.00.00"

SRCREV = "3e3b06070c32395108af17869b8a443ea9824135"
PR = "${INC_PR}.10"
PR_append = "+gitr${SRCPV}"

PSPREL = "04.06.00.02"

UBOOT_MACHINE_ti33x = "am335x_evm_config"
