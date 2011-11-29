DESCRIPTION = "Oprofile demo applications for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r2"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/740/5237/oprofiledemos_1.2.tar.gz"

S = ${WORKDIR}/oprofiledemos

require matrix-gui-apps.inc

# Make sure profiling submenu has been installed
RDEPENDS +=  "matrix-gui-submenus-oprofile"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "56c081ad8f4d301994558b8363055665"
SRC_URI[sha256sum] = "fa03139120ebbe703d49f3b86a44fdeff190c14dbb3cdbbeca22ac249b0c12e2"
