require u-boot_2011.09.inc

BRANCH = "int_am335xpsp_04.06.00.01-v2011.09-for-sdk-05.03.01.00"

SRCREV = "0acf7393b052eb1c1dc3cd93c1e7c90f647cf335"
PR = "${INC_PR}.11"
PR_append = "+gitr${SRCPV}"

PSPREL = "04.06.00.03"
