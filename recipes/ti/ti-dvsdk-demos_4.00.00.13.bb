
COMPATIBLE_MACHINE = "(dm365-evm)"

PV = "4_00_00_13"
SRCREV         = "9742"
DEMOSBRANCH    = "tags/TAG_${PV}"

RDEPENDS_append_dm365 = " ti-dvsdk-demos-qtinterface "
require ti-dvsdk-demos.inc

