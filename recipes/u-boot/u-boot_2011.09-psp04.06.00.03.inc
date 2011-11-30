require u-boot_2011.09.inc

BRANCH = "int_am335xpsp_04.06.00.01-v2011.09-for-sdk-05.03.01.00"

SRCREV = "d3ae10f02422b3add681ee09d1b25dec68280288"
PR = "${INC_PR}.12"
PR_append = "+gitr${SRCPV}"

PSPREL = "04.06.00.03"
