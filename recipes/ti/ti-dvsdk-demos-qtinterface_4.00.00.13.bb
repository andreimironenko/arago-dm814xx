
COMPATIBLE_MACHINE = "(dm365-evm)"

PV = "4_00_00_13"
SRCREV         = "9742"
DEMOSBRANCH    = "tags/TAG_${PV}"

require ti-dvsdk-demos-qtinterface.inc
PR = "r0"
S_dm365  = "${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos/demos/qtInterface"

