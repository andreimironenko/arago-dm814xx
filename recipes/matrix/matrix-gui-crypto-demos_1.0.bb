DESCRIPTION = "Cryptography demo descriptions for Matrix v2"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/matrix-gui-v2-apps"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

require matrix-gui-apps.inc

PR = "${INC_PR}.5"

PACKAGE_ARCH = "all"

S = ${WORKDIR}/git/cryptos_apps

# Make sure crypto submenu and app images has been installed. Also make sure openssl is available
RDEPENDS +=  "matrix-gui-apps-images matrix-gui-submenus-cryptos openssl"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"
