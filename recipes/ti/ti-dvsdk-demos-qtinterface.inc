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
DEPENDS_append = " qt4-embedded "
RDEPENDS_append = " qt4-embedded "

do_install() {
	install -d ${D}/${installdir}/dvsdk-demos
	install -m 0755 ${S}/qtInterface ${D}/${installdir}/dvsdk-demos
}

FILES_${PN} = "${installdir}/dvsdk-demos/*"


