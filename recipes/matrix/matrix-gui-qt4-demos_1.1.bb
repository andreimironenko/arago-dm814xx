DESCRIPTION = "Qt4 demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r3"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/686/5110/qt4demos_1.4.tar.gz"

S = ${WORKDIR}/qt4demos

require matrix-gui-apps.inc

# Make sure qt4 submenu has been installed
RDEPENDS +=  matrix-gui-submenus-qt4

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "a293e5f5324ea235ed3dc4bb47556686"
SRC_URI[sha256sum] = "2cbc049900fcd92fd393d3719c8ce38efad8b8d823ddd1d57fc12045541e95cb"
