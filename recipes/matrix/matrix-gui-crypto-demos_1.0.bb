DESCRIPTION = "Cryptography demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r1"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/667/5090/cryptodemos_1.1.tar.gz"

S = ${WORKDIR}/cryptodemos

require matrix-gui-apps.inc

# Make sure crypto submenu has been installed and openssl is available
RDEPENDS +=  "matrix-gui-submenus-cryptos openssl"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "b2d4d565d41d1f740dcc22d7e190a11e"
SRC_URI[sha256sum] = "6e6efa0c5202be82cd2d325f60d385a71f61b5754b1cfcfe3230bf061dfeb6ad"
