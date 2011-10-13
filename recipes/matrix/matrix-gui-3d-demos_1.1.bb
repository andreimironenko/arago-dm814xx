DESCRIPTION = "3D demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r1"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/661/5063/3ddemos_1.1.tar.gz"

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

SRC_URI[md5sum] = "0359fc2bca2ac8148a6dbe7451bce8f7"
SRC_URI[sha256sum] = "bfd4344aa95bb2a7dc783b067d93e152915c2fb4da490d072a459859a0e5c84a"
