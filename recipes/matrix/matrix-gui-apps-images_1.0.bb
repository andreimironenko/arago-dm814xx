DESCRIPTION = "Image package for Matrix GUI v2 Applications"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/matrix-gui-v2-apps"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

require matrix-gui-apps-git.inc
require matrix-gui-paths.inc

PR = "${INC_PR}.0"

PACKAGE_ARCH = "all"

S = "${WORKDIR}/git/images"

do_install(){
    install -d ${D}${MATRIX_APP_DIR}
    cp -rf ${S}/ ${D}${MATRIX_APP_DIR}
}

FILES_${PN} += "${MATRIX_BASE_DIR}/*"
