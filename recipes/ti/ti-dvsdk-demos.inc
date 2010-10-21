DESCRIPTION = "DVSDK Demo applications for TI ARM/DSP processors"
SECTION = "multimedia"
LICENSE = "TI"

INC_PR = "r17"
 
DEPENDS += "ti-dmai"
DEPENDS += "alsa-lib libpng freetype jpeg"
DEPENDS_append_omap3 = " ti-c6accel "

require ti-paths.inc
require ti-staging.inc

DEPENDS += "ti-dmai"
DEPENDS += "alsa-lib libpng freetype jpeg"
DEPENDS_append_omap3 = " ti-c6accel "

DEMOSPLATFORM_dm355    = "dm355"
DEMOSPLATFORM_dm365    = "dm365"
DEMOSPLATFORM_dm6446   = "dm6446"
DEMOSPLATFORM_dm6467   = "dm6467"
DEMOSPLATFORM_omap3    = "omap3530"

PR = "${MACHINE_KERNEL_PR}"
PR_append = "a"

S  = "${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos/dvsdk_demos_${PV}"

do_preparesource () {
    DEMOS=${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos
    find ${DEMOS} -name .svn -type d | xargs rm -rf
    cp -pPrf ${WORKDIR}/doxygen_templates ${DEMOS}
    cp -pPrf ${WORKDIR}/arago-tdox ${DEMOS}/tdox
    chmod a+x ${DEMOS}/release.sh
    chmod a+x ${DEMOS}/tdox
    cd ${DEMOS}
    ./release.sh ${PV} ${DEMOSPLATFORM}
    rm -rf ${DEMOS}/doxygen_templates
    rm -rf ${DEMOS}/tdox
}

# run prepare sources before do_patch to ensure that sourceipk picks the right sources.
addtask preparesource after do_unpack before do_patch

SRC_URI = "svn://winsvn.sanb.design.ti.com/SDOApps/apps/dvsdk_demos/;module=${DEMOSBRANCH};proto=http;user=anonymous;pswd='' \
	file://doxygen_templates.tar.gz \
	file://arago-tdox "

PR = "${INC_PR}.1"
S  = "${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos/dvsdk_demos_${PV}"

do_preparesource () {
    DEMOS=${WORKDIR}/${DEMOSBRANCH}/dvsdk_demos
    find ${DEMOS} -name .svn -type d | xargs rm -rf
    cp -pPrf ${WORKDIR}/doxygen_templates ${DEMOS}
    cp -pPrf ${WORKDIR}/arago-tdox ${DEMOS}/tdox
    chmod a+x ${DEMOS}/release.sh
    chmod a+x ${DEMOS}/tdox
    cd ${DEMOS}
    ./release.sh ${PV} ${DEMOSPLATFORM}
    rm -rf ${DEMOS}/doxygen_templates
    rm -rf ${DEMOS}/tdox
}

# run prepare sources before do_patch to ensure that sourceipk picks the right sources.
addtask preparesource after do_unpack before do_patch

do_configure_prepend() {
    sed -i -e 's:INSTALLSUBDIRS = $(addsuffix .install, $(SUBDIRS)):INSTALLSUBDIRS = $(addsuffix .install, $(PLATFORM)):g' ${S}/Makefile
}

do_compile () {
	cd ${S}
	make -e DMAI_INSTALL_DIR="${DMAI_INSTALL_DIR}" \
		XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
		CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
		FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
		CODEC_INSTALL_DIR="${CODEC_INSTALL_DIR}" \
		C6ACCEL_INSTALL_DIR="${C6ACCEL_INSTALL_DIR}" \
		CODEGEN_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
		BIOS_INSTALL_DIR="${BIOS_INSTALL_DIR}"\
		CROSS_COMPILE="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
		XDAIS_INSTALL_DIR="${XDAIS_INSTALL_DIR}" \
		LINK_INSTALL_DIR="${LINK_INSTALL_DIR}" \
		CMEM_INSTALL_DIR="${CMEM_INSTALL_DIR}" \
		LPM_INSTALL_DIR="${LPM_INSTALL_DIR}" \
		LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
		LINUXLIBS_INSTALL_DIR="${STAGING_DIR_TARGET}/usr" \
		MVTOOL_DIR="${TOOLCHAIN_PATH}" \
                CSTOOL_DIR="${TOOLCHAIN_PATH}" \
                CSTOOL_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
                MVTOOL_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
		${DEMOSPLATFORM}
}

do_install () {
	cd ${S}
	make -e EXEC_DIR=${D}/${installdir}/dvsdk-demos \
		install
}

pkg_postinst_${PN} () {
	ln -sf ${installdir}/ti-codecs-server/*.${DSPSUFFIX} ${installdir}/dvsdk-demos/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_ti-dvsdk-demos_dm355 += "ti-dm355mm-module ti-cmem-module alsa-lib libpng freetype jpeg libpng12 ti-dmai"
RDEPENDS_ti-dvsdk-demos_dm365 += "ti-dm365mm-module ti-cmem-module alsa-lib libpng freetype jpeg  ti-edma-module ti-irq-module libpng12 ti-dmai"
RDEPENDS_ti-dvsdk-demos_omap3 += "ti-cmem-module alsa-lib libpng freetype jpeg ti-codecs-omap3530-server ti-dsplink-module ti-c6accel libpng12 ti-dmai"

FILES_${PN} = "${installdir}/dvsdk-demos/*"
PACKAGE_STRIP = "no"
INSANE_SKIP_${PN} = "True"

