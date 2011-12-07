require u-boot_2011.09.inc

BRANCH = "int_am335xpsp_04.06.00.01-v2011.09-for-sdk-05.03.01.00"

SRCREV = "7314b406e7b4182fa6a6cbf5d8b76650cf534188"
PR = "${INC_PR}.18"
PR_append = "+gitr${SRCPV}"

SRC_URI_append_beagleboard = " file://0001-omap3_beagle-fix-network-boot-support.patch"

PSPREL = "04.06.00.03"
