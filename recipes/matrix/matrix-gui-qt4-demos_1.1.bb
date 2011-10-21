DESCRIPTION = "Qt4 demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r1"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/677/5100/qt4demos_1.2.tar.gz"

S = ${WORKDIR}/qt4demos

require matrix-gui-apps.inc

# Make sure qt4 submenu has been installed
RDEPENDS +=  matrix-gui-submenus-qt4

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "85344ae272a78466aa80e06610254500"
SRC_URI[sha256sum] = "4f40e21229a2716d184c970b18ff8a782427a41a28589fe674f665d6a911cc2a"
