
COMPATIBLE_MACHINE = "(dm365-evm|dm37x-evm|omap3evm)"

PV = "4_00_00_18"
SRCREV         = ""
DEMOSBRANCH    = ""

PR = "r0"

require ti-dvsdk-demos.inc

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/oss_sw_packages/dvsdk_4_demos/dvsdk_demos_${PV}.tar.gz;name=demotarball \
	file://doxygen_templates.tar.gz \
	file://arago-tdox "

SRC_URI[demotarball.md5sum] = "a74ea2d0d98e96ee5599f7badfc4750b"
SRC_URI[demotarball.sha256sum] = "3094cf63e428df36123b60de31d6fe8bc80984ac35f60ccaa378ad98e5f23cbb"

RDEPENDS_append = " ti-dvsdk-demos-qtinterface "
