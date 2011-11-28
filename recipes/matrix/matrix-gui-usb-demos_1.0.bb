DESCRIPTION = "USB demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r3"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/732/5235/usbdemos_1.4.tar.gz"

S = ${WORKDIR}/usbdemos

require matrix-gui-apps.inc

# Make sure qt4 submenu has been installed
RDEPENDS +=  "matrix-gui-submenus-usb bonnie++"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "f4e3f6b2e8757145bb6a2f1f56d6e75a"
SRC_URI[sha256sum] = "49c298bca0a1507ce01cc54a048afaadff69888e8bd54fcf9abeb64f88340d46"
