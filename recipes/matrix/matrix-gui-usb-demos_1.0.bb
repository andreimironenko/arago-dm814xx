DESCRIPTION = "USB demo descriptions for Matrix v2"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/matrix-gui-v2-apps"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

require matrix-gui-apps.inc

PR = "${INC_PR}.4"

PACKAGE_ARCH = "all"

S = ${WORKDIR}/git/usb_apps

# Make sure usb submenu and app images has been installed
RDEPENDS +=  "matrix-gui-apps-images matrix-gui-submenus-usb bonnie++"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"
