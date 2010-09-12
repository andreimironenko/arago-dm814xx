
COMPATIBLE_MACHINE = "(omap3evm|dm37x-evm)"

PV = "3_01_00_29"
SRCREV         = "9595"
DEMOSBRANCH    = "tags/TAG_${PV}"
SRC_URI_append = "file://omap3-loadmodule-fix.patch "

require ti-dvsdk-demos.inc

