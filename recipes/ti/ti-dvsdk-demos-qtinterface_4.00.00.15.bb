
COMPATIBLE_MACHINE = "(dm365-evm|dm37x-evm|omap3evm)"

PV = "4_00_00_15"
SRCREV         = "9776"
DEMOSBRANCH    = "tags/TAG_${PV}"

require ti-dvsdk-demos-qtinterface.inc
PR = "r0"
S = "${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos/demos/qtInterface"
