DESCRIPTION = "User space DMA module for DM365"
SECTION = "devel"
LICENSE = "GPLv2"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/dvsdk/DVSDK_3_10/latest/exports/dm365mm_${PV}.tar.gz;name=dm365mmtarball"

S = "${WORKDIR}/dm365mm_${PV}"

inherit module
require ti-paths.inc
require ti-staging.inc

do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    find ${S} -name "*.ko" -exec rm {} \; || true
    cd ${S}/module
    make \
      LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
      MVTOOL_PREFIX="${TARGET_PREFIX}";
}

do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp
    install -m 0755 ${S}/module/dm365mmap.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dsp

    install -d ${D}${DM365MM_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${DM365MM_INSTALL_DIR_RECIPE}
}

PACKAGE_STRIP = "no"
INSANE_SKIP_${PN} = True
