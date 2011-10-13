DESCRIPTION = "Qt4 demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r0"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/664/5071/qt4demos_1.1.tar.gz"

S = ${WORKDIR}/qt4demos

require matrix-gui-apps.inc

# Make sure qt4 submenu has been installed
RDEPENDS +=  matrix-gui-submenus-qt4

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "0c514ad64c44fc795031fc6640362dca"
SRC_URI[sha256sum] = "4109e5b36ea19d8a87442ab78d26ba0801affa71fbb634d53e8e0239518ce5d5"
