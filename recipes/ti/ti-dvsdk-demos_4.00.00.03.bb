
COMPATIBLE_MACHINE = "(dm365-evm)"
PV = "4_00_00_03"
SRCREV         = "9631"
DEMOSBRANCH    = "tags/TAG_${PV}"
SRC_URI_append = "file://dm365-loadmodule-fix.patch"

RDEPENDS_append = " ti-dvsdk-demos-qtinterface "
require ti-dvsdk-demos.inc

