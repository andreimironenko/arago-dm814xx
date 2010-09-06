require ti-dvsdk-demos.inc

COMPATIBLE_MACHINE = "(omap3evm|dm37x-evm)"

SRCREV         = "9552"
DEMOSBRANCH    = "tags/TAG_${PV}"

SRC_URI = "svn://winsvn.sanb.design.ti.com/SDOApps/apps/dvsdk_demos/;module=${DEMOSBRANCH};proto=http;user=anonymous;pswd='' \
	file://doxygen_templates.tar.gz \
	file://arago-tdox "

PV = "3_01_00_26"
PR = "${INC_PR}.1"
S  = "${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos/demos"

