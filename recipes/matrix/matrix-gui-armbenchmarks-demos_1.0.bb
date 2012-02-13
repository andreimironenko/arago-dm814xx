DESCRIPTION = "Arm Benchmarks Applications for Matrix v2"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/matrix-gui-v2-apps"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

require matrix-gui-apps.inc

PR = "${INC_PR}.0"

PACKAGE_ARCH = "all"

S = ${WORKDIR}/git/arm_apps

# Make sure arm submenu and app images has been installed
RDEPENDS += matrix-gui-apps-images matrix-gui-submenus-arm

FILES_${PN} += "${MATRIX_BASE_DIR}/*"
