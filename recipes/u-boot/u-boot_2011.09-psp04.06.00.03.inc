require u-boot_2011.09.inc

BRANCH = "int_am335xpsp_04.06.00.01-v2011.09-for-sdk-05.03.01.00"

SRCREV = "53909d0c895ad8b0bda20511b7cbafee8e7f5d3d"
PR = "${INC_PR}.13"
PR_append = "+gitr${SRCPV}"

PSPREL = "04.06.00.03"
