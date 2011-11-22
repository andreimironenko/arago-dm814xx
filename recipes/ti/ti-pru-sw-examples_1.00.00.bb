DESCRIPTION = "PRU sw example applications"
HOMEPAGE = "https://gforge.ti.com/gf/project/pru_sw/"
LICENSE = "BSD"
DEPENDS = "ti-pru-sw-app-loader ti-pru-sw-edma-library"
RDEPENDS = "ti-pru-sw-edma-driver"
PR = "r5+svnr${SRCPV}"

COMPATIBLE_MACHINE = "omapl138|am180x-evm"

SRC_URI = "svn://gforge.ti.com/svn/pru_sw/;module=trunk;proto=https;user=anonymous;pswd='' \
           https://gforge.ti.com/gf/download/frsrelease/727/5211/prudemos_1.1.tar.gz"

SRCREV = "33"
S = "${WORKDIR}/trunk"

MATRIX_FILES_DIR = "${WORKDIR}/prudemos"
require recipes/matrix/matrix-gui-apps.inc

do_compile () {
        make -C ${S}/example_apps \
          CCTOOLS="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}"\
          BINDIR_APPLICATIONS="${S}/example_apps/bin" \
          BINDIR_FW="${S}/example_apps/bin" \
          UTILS_DIR="${S}/utils"
}

do_install () {
        install -d ${D}/usr/share/ti/ti-pru-eg/
        install -m 0755 ${S}/example_apps/bin/* ${D}/usr/share/ti/ti-pru-eg/
}

FILES_${PN} += "${datadir}/ti/ti-pru-eg/*"
FILES_${PN}-dbg += "${datadir}/ti/ti-pru-eg/.debug/*"

# Make sure the pru submenu has been installed
RDEPENDS += matrix-gui-submenus-pru

# Add the matrix directory to the list of FILES
FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "fe36ad41513857429d4e9c5a271e7d59"
SRC_URI[sha256sum] = "018cc8a2468021bcf61dd74fd09e0184d74806e1db13e066ab5004e70385aff3"
