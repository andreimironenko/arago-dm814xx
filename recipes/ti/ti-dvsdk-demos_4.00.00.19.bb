
COMPATIBLE_MACHINE = "(dm365|omap3)"

PV = "4_00_00_19"
SRCREV         = "9885"
DEMOSBRANCH    = "trunk"

SRC_URI = "svn://winsvn.sanb.design.ti.com/SDOApps/apps/dvsdk_demos/;module=${DEMOSBRANCH};proto=http;user=anonymous;pswd='' \
	file://doxygen_templates.tar.gz \
	file://arago-tdox "

require ti-dvsdk-demos.inc
RDEPENDS_append = " ti-dvsdk-demos-qtinterface "

