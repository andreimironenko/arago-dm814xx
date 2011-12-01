DESCRIPTION = "PRU sw example applications"
HOMEPAGE = "https://gforge.ti.com/gf/project/pru_sw/"
LICENSE = "BSD"
DEPENDS = "ti-pru-sw-app-loader ti-pru-sw-edma-library"
RDEPENDS = "ti-pru-sw-edma-driver"
PR = "r6+svnr${SRCPV}"

COMPATIBLE_MACHINE = "omapl138|am180x-evm"

SRC_URI = "svn://gforge.ti.com/svn/pru_sw/;module=trunk;proto=https;user=anonymous;pswd='' \
           https://gforge.ti.com/gf/download/frsrelease/756/5268/prudemos_1.2.tar.gz"

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

SRC_URI[md5sum] = "365d84718530b1cecc50e920f7f48792"
SRC_URI[sha256sum] = "2aa65cb6a4c904fead23580d2791c175aa56dcb04da9a899e2266e54200833f4"
