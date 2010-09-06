require ti-dvsdk-demos.inc

COMPATIBLE_MACHINE = "(dm365-evm)"

PV = "4_00_00_00"
SRCREV         = "9544"
DEMOSBRANCH    = "tags/TAG_${PV}"

SRC_URI = "svn://winsvn.sanb.design.ti.com/SDOApps/apps/dvsdk_demos/;module=${DEMOSBRANCH};proto=http;user=anonymous;pswd='' \
	file://doxygen_templates.tar.gz \
	file://arago-tdox "

PR = "${INC_PR}.1"
S  = "${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos/demos"

