require u-boot_2011.09.inc

BRANCH = "int_am335xpsp_04.06.00.01-v2011.09-for-sdk-05.03.01.00"

SRCREV = "9bcb43fff35ab96eda791481a9381f32ffb3ea14"
PR = "${INC_PR}.15"
PR_append = "+gitr${SRCPV}"

PSPREL = "04.06.00.03"
