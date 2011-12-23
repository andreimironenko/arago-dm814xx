DESCRIPTION = "Settings demo descriptions for Matrix v2"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/matrix-gui-v2-apps"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

require matrix-gui-apps.inc

PR = "${INC_PR}.0"

PACKAGE_ARCH = "all"

S = ${WORKDIR}/git/settings_apps

# Make sure setting submenu and app images has been installed
RDEPENDS +=  matrix-gui-apps-images matrix-gui-submenus-settings

FILES_${PN} += "${MATRIX_BASE_DIR}/*"
