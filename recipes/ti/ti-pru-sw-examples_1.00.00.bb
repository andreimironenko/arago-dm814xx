DESCRIPTION = "PRU sw example applications"
HOMEPAGE = "https://gforge.ti.com/gf/project/pru_sw/"
LICENSE = "BSD"
DEPENDS = "ti-pru-sw-app-loader ti-pru-sw-edma-library"
PR = "r2+svnr${SRCPV}"

COMPATIBLE_MACHINE = "omapl138|am180x-evm"

SRC_URI = "svn://gforge.ti.com/svn/pru_sw/;module=trunk;proto=https;user=anonymous;pswd='' \
           https://gforge.ti.com/gf/download/frsrelease/613/4893/prudemos_1.0.tar.gz"

SRCREV = "31"
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

SRC_URI[md5sum] = "b09a7be3fe7a879a2d81cf40f6e1b0e9"
SRC_URI[sha256sum] = "bc6a746e4ea220fc0574b131ae35c9abdd0af1214fefa3a3d4e8dd7dca6a0d52"
