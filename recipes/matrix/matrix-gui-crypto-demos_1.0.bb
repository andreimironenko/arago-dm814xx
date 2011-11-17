DESCRIPTION = "Cryptography demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/arm_crypto/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r3"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/728/5212/cryptodemos_1.3.tar.gz"

S = ${WORKDIR}/cryptodemos

require matrix-gui-apps.inc

# Make sure crypto submenu has been installed and openssl is available
RDEPENDS +=  "matrix-gui-submenus-cryptos openssl"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "ffae6e34d79c900c7a4f6a61186256d2"
SRC_URI[sha256sum] = "bf7a015d031d57e751185d92ce0eb4f1d9e8bb89d9760c2d6b362a8ae8b9c3ae"
