DESCRIPTION = "3D demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r2"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/672/5095/3ddemos_1.2.tar.gz"

S = ${WORKDIR}/3ddemos

require matrix-gui-apps.inc

# Make sure 3D submenu has been installed
# TODO: in the future we may want to consider putting this into the libgles
#       recipe directly.  Requires broad acceptance of matrix v2 though due
#       to the matrix-gui-submenus-3d dependency.  So if matrix v2 moves
#       into the same layer as libgles this may be acceptable, or perhaps
#       we can use an RRECOMMENDS instead.
RDEPENDS +=  "matrix-gui-submenus-3d libgles-omap3-rawdemos"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "daacfcefac0721be388d88f4cb8284e6"
SRC_URI[sha256sum] = "22b8e1eb775970b1027a881ce37ab998526503cb5a0de540400f52e642276a0c"
