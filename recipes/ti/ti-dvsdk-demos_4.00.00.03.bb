
COMPATIBLE_MACHINE = "(dm365-evm)"
PV = "4_00_00_03"
SRCREV         = "9631"
DEMOSBRANCH    = "tags/TAG_${PV}"

RDEPENDS_append = " ti-dvsdk-demos-qtinterface "
require ti-dvsdk-demos.inc

