DESCRIPTION = "3D demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r0"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/661/5063/3ddemos_1.1.tar.gz"

S = ${WORKDIR}/3ddemos

require matrix-gui-apps.inc

# Make sure 3D submenu has been installed
RDEPENDS +=  matrix-gui-submenus-3d

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "0359fc2bca2ac8148a6dbe7451bce8f7"
SRC_URI[sha256sum] = "bfd4344aa95bb2a7dc783b067d93e152915c2fb4da490d072a459859a0e5c84a"
