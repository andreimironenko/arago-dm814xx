DESCRIPTION = "Media files for TI DVSDK"
SECTION = "multimedia"
LICENSE = "TI"

PR = "r7"

S = "${WORKDIR}/${DEMOSPLATFORM}"

require ti-paths.inc

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/avdata/latest/exports/${DEMOSPLATFORM}_avdata_${PV}.tar.gz;name=${DEMOSPLATFORM}_datatarball"

# Since omap3530 clips bundle does not have 720 clips hence download 365 clip bundle and use 720 clips from 365 platform.
SRC_URI_append_dm37x-evm = " http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/avdata/latest/exports/dm365_avdata_${PV}.tar.gz;name=dm365_datatarball "

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEMOSPLATFORM_dm355    = "dm355"
DEMOSPLATFORM_dm365    = "dm365"
DEMOSPLATFORM_dm6467   = "dm6467"
DEMOSPLATFORM_omapl138   = "omapl138"
DEMOSPLATFORM_omap3   = "omap3530"
DEMOSPLATFORM_ti816x   = "ti816x"

do_compile () {
	:
}

do_install () {
       install -d ${D}/${installdir}
       cp -pPrf ${S}/data ${D}/${installdir}
}

do_install_append_dm37x-evm () {
       # delete D1 clips
       rm -rf ${D}/${installdir}/data/videos/davincieffect_480p30.264
       rm -rf ${D}/${installdir}/data/videos/davincieffect_480p30.m4v
       rm -rf ${D}/${installdir}/data/videos/davincieffect_480p30.mp4

       # copy 720 clips
       cp ${WORKDIR}/dm365/data/videos/davincieffect.264 ${D}/${installdir}/data/videos/
       cp ${WORKDIR}/dm365/data/videos/davincieffect_h264_aac.mp4 ${D}/${installdir}/data/videos/
       cp ${WORKDIR}/dm365/data/videos/davincieffect.mpeg4 ${D}/${installdir}/data/videos/
}

FILES_${PN} = "${installdir}/*"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = "True"

