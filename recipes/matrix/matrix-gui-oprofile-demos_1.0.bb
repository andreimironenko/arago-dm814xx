DESCRIPTION = "Oprofile demo applications for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r4"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/757/5267/oprofiledemos_1.4.tar.gz"

S = ${WORKDIR}/oprofiledemos

require matrix-gui-apps.inc

# Make sure profiling submenu has been installed
RDEPENDS +=  "matrix-gui-submenus-oprofile"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "e7f014db3ee7af574402fd6b35680fd0"
SRC_URI[sha256sum] = "9d9d8f9f05cc1deb3fab74bdb9df77f7a7af09438cc6b5e4a2fa36a8bfe08e0b"
