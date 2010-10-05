
COMPATIBLE_MACHINE = "(dm365-evm|dm37x-evm|omap3evm)"

PV = "4_00_00_08"
SRCREV         = "9672"
DEMOSBRANCH    = "tags/TAG_${PV}"

RDEPENDS_append_dm365 = " ti-dvsdk-demos-qtinterface "
require ti-dvsdk-demos.inc

