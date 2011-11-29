DESCRIPTION = "Power management demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r2"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/742/5239/pmdemos_1.2.tar.gz"

S = ${WORKDIR}/pmdemos

require matrix-gui-apps.inc

# Make sure power submenu has been installed
RDEPENDS +=  matrix-gui-submenus-power

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "ac7eea0321a80a3b4ff27fe37d23ec3c"
SRC_URI[sha256sum] = "163c13e98f94ef55740edbb7fc77e49e708ca0838d5a2015dcdd067b8cac49aa"
