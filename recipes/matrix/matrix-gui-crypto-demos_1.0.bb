DESCRIPTION = "Cryptography demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/arm_crypto/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r4"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/736/5225/cryptodemos_1.4.tar.gz"

S = ${WORKDIR}/cryptodemos

require matrix-gui-apps.inc

# Make sure crypto submenu has been installed and openssl is available
RDEPENDS +=  "matrix-gui-submenus-cryptos openssl"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "7d2764ae6e4e0d019786cc55b246cc4a"
SRC_URI[sha256sum] = "3a52dd4000f58be66c63c06d7139ff8eaee35f543ca34fa0b8ec4c4d4008ca6d"
