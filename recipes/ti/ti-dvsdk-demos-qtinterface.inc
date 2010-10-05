DESCRIPTION = "DVSDK Demo applications for TI ARM/DSP processors"
SECTION = "multimedia"
LICENSE = "TI"

inherit qt4e

DEMOSPLATFORM_dm355    = "dm355"
DEMOSPLATFORM_dm365    = "dm365"
DEMOSPLATFORM_dm6446   = "dm6446"
DEMOSPLATFORM_dm6467   = "dm6467"
DEMOSPLATFORM_omap3    = "omap3530"
DEMOSPLATFORM_omapl137 = "ol137"
DEMOSPLATFORM_omapl138 = "ol138"

installdir="/usr/share/ti"

SRC_URI = "svn://winsvn.sanb.design.ti.com/SDOApps/apps/dvsdk_demos/;module=${DEMOSBRANCH};proto=http;user=anonymous;pswd=''" 

S  = "${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos/demos/${DEMOSPLATFORM}/qtInterface"

CXXFLAGS_dm365_append = " -DPlatform_dm365 "
CXXFLAGS_omap3_append = " -DPlatform_omap3530 "

do_install() {
	install -d ${D}/${installdir}/dvsdk-demos
	install -m 0755 ${S}/qtInterface ${D}/${installdir}/dvsdk-demos
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/dvsdk-demos/*"
RRECOMMENDS_${PN} = "qt4-embedded-plugin-mousedriver-tslib"

