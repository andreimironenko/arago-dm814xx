DESCRIPTION = "Camera loopback application for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r0"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/748/5250/cameraloopback_1.0.tar.gz"

S = ${WORKDIR}

require matrix-gui-apps.inc

# Make sure loopback application is compiled
RDEPENDS +=  "av-examples"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "c3ecdd7a717876335d9d9488acf88fac"
SRC_URI[sha256sum] = "c36ccc325db70dc3b2ec514f1bce89971aef1bb089694ad543aca138d8c29cf2"
