DESCRIPTION = "DVTB for TI ARM/DSP processors"
SECTION = "multimedia"
LICENSE = "TI"

PR = "r2"
 
#DEPENDS += "ti-codecs ti-codec-engine ti-linuxutils ti-xdais"
DEPENDS += "alsa-lib jpeg"

SRC_URI = "http://gt_tspa_dev.gt.design.ti.com/dev/DVSDK/310_DVSDK/3_10_00/dvtb_${PV}.tar.gz;name=dvtbtarball"

S = "${WORKDIR}/dvtb_${PV}"

require ti-paths.inc
require ti-staging.inc

DVTBPLATFORM_dm355    = "dm355"
DVTBPLATFORM_dm365    = "dm365"
DVTBPLATFORM_dm6467   = "dm6467"

do_compile () {
	cd ${S}
	make clean
	make PLATFORM=${DVTBPLATFORM} \
		VERBOSE="true" \
		DMAI_INSTALL_DIR="${DMAI_INSTALL_DIR}" \
		XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
		CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
		FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
		CODEC_INSTALL_DIR="${CODEC_INSTALL_DIR}" \
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
		MVTOOL_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
		${DVTBPLATFORM}
}

do_install () {
        install -d ${D}${DVTB_INSTALL_DIR_RECIPE}
        cp -pPrf ${S}/* ${D}${DVTB_INSTALL_DIR_RECIPE}

        # DVTB does not currently support make install, so copying files manually.
        install -d ${D}/${installdir}/dvtb
        cp ${S}/packages/ti/sdo/dvtb/dm365/bin/* ${D}/${installdir}/dvtb
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_ti-dvtb_dm6467 += "ti-cmem-module alsa-lib jpeg"
RDEPENDS_ti-dvtb_dm355  += "ti-dm355mm-module ti-cmem-module alsa-lib jpeg"
RDEPENDS_ti-dvtb_dm365  += "ti-dm365mm-module ti-cmem-module alsa-lib jpeg"

FILES_${PN} = "${installdir}/dvtb/*"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} = "True"
