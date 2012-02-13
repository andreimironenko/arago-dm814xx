COMPATIBLE_MACHINE = "(dm365|omap3)"

PV = "4_00_00_18"
SRCREV         = ""
DEMOSBRANCH    = ""

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/oss_sw_packages/dvsdk_4_demos/dvsdk_demos_${PV}.tar.gz;name=demotarball \
file://0001-messageserver-include-stdio.h-to-provide-fopen-fgets.patch"

SRC_URI[demotarball.md5sum] = "a74ea2d0d98e96ee5599f7badfc4750b"
SRC_URI[demotarball.sha256sum] = "3094cf63e428df36123b60de31d6fe8bc80984ac35f60ccaa378ad98e5f23cbb"

require ti-dvsdk-demos-qtinterface.inc
S = "${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos/demos/qtInterface"
