DESCRIPTION = "Power management demo descriptions for Matrix v2"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/matrix-gui-v2-apps"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

require matrix-gui-apps.inc

PR = "${INC_PR}.4"

PACKAGE_ARCH = "all"

S = ${WORKDIR}/git/power_apps

# Make sure power submenu and app images has been installed
RDEPENDS +=  matrix-gui-apps-images matrix-gui-submenus-power

FILES_${PN} += "${MATRIX_BASE_DIR}/*"
