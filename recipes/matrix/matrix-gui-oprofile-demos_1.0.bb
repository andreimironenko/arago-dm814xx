DESCRIPTION = "Oprofile demo applications for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r1"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/733/5222/oprofiledemos_1.1.tar.gz"

S = ${WORKDIR}/oprofiledemos

require matrix-gui-apps.inc

# Make sure profiling submenu has been installed
RDEPENDS +=  "matrix-gui-submenus-oprofile"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "db25ffb8a90d870e5ff7d5926bde19b7"
SRC_URI[sha256sum] = "af1af76fd7a118e751fb618226a754db5f6d90d6c8416dc037b45b0b28d94a7d"
