DESCRIPTION = "USB demo descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r2"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/696/5123/usbdemos_1.3.tar.gz"

S = ${WORKDIR}/usbdemos

require matrix-gui-apps.inc

# Make sure qt4 submenu has been installed
RDEPENDS +=  "matrix-gui-submenus-usb bonnie++"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "f2b5519099ca491126d6c60f26a20239"
SRC_URI[sha256sum] = "fbae40a980fb365631b0009f3f7e2e16ca7b7f1746845caf64f9612adbf6ded4"
