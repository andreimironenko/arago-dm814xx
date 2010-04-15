DESCRIPTION = "Media files for TI DVSDK"
SECTION = "multimedia"
LICENSE = "TI"

PR = "r2"

S = "${WORKDIR}/${DEMOSPLATFORM}"

require ti-paths.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEMOSPLATFORM_dm355    = "dm355"
DEMOSPLATFORM_dm365    = "dm365"
DEMOSPLATFORM_dm6467   = "dm6467"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/AVDATA/${PV}/exports/${DEMOSPLATFORM}_avdata_${PV}.tar.gz;name=${DEMOSPLATFORM}_datatarball"

do_compile () {
       cd ${S}
}

do_install () {
       install -d ${D}/${installdir}
       cp -pPrf ${S}/data ${D}/${installdir}
}

FILES_${PN} = "${installdir}/*"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = "True"
