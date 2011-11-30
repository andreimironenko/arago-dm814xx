DESCRIPTION = "Oprofile demo applications for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r3"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/747/5243/oprofiledemos_1.3.tar.gz"

S = ${WORKDIR}/oprofiledemos

require matrix-gui-apps.inc

# Make sure profiling submenu has been installed
RDEPENDS +=  "matrix-gui-submenus-oprofile"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "f70708c34eb1911cf95a121909c6bcb4"
SRC_URI[sha256sum] = "ecd98cf21b9cbf597af62935a7d2e0e05ac5b6f99521826713ec1605dddc6861"
