DESCRIPTION = "Linux dirver examples/test application of OMAP_L138 "
HOMEPAGE = " "

SECTION = "devel"
LICENSE = "TI BSD"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "(da850-omapl138-evm)"

PR = "r1"

S = "${WORKDIR}/examples-${PV_dot}"

SRC_URI = "ftp://nemo.sc.ti.com/pub/int-packages/omapl138_psp_examples_${PV}.tar.gz;name=linuxexamplestarball"

DEPENDS += "virtual/kernel"

do_compile() {
	cd ${S}/vpif
	make CROSS_COMPILE="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" KERNEL_PATH="{STAGING_KERNEL_DIR}"
}


do_install() {
    install -d ${D}${LINUXEXAMPLE_SOURCE_DIR}
    cp -pPrf ${S}/* ${D}${LINUXEXAMPLE_SOURCE_DIR}

    install -d ${D}/${installdir}/linux-driver-examples
    cp -rf ${S}/vpif/vpif_test_display ${D}/${installdir}/linux-driver-examples
    cp -rf ${S}/vpif/vpif_test_mmap_loopback ${D}/${installdir}/linux-driver-examples
    cp -rf ${S}/vpif/vpif_test_userptr_loopback ${D}/${installdir}/linux-driver-examples
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/linux-driver-examples/*"
INSANE_SKIP_${PN} = "True"

