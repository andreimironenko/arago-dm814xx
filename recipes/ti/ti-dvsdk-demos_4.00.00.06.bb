
COMPATIBLE_MACHINE = "(dm365-evm)"
PV = "4_00_00_06"
SRCREV         = "9649"
DEMOSBRANCH    = "tags/TAG_${PV}"

RDEPENDS_append = " ti-dvsdk-demos-qtinterface "
require ti-dvsdk-demos.inc

