DESCRIPTION = "Clock setting descriptions for Matrix v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r2"

PACKAGE_ARCH = "all"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/729/5213/clocks_1.2.tar.gz"

S = ${WORKDIR}/clocks

require matrix-gui-apps.inc

# Make sure power submenu has been installed
RDEPENDS +=  matrix-gui-submenus-power

# Break out the individual files into separate packages.  That way only the
# clocks supported for each device can be installed.  Prepend the list so
# that we can get the files in ${bindir} first
PACKAGES =+ "${PN}-300mhz ${PN}-600mhz ${PN}-800mhz ${PN}-1ghz"

# Split the matrix files by clock
FILES_${PN}-300mhz += "${MATRIX_APP_DIR}/power_set_300mhz/*"
FILES_${PN}-600mhz += "${MATRIX_APP_DIR}/power_set_600mhz/*"
FILES_${PN}-800mhz += "${MATRIX_APP_DIR}/power_set_800mhz/*"
FILES_${PN}-1ghz += "${MATRIX_APP_DIR}/power_set_1ghz/*"

# Split the ${bindir} files by clock
FILES_${PN}-300mhz += "${bindir}/setopp2.sh"
FILES_${PN}-600mhz += "${bindir}/setopp3.sh"
FILES_${PN}-800mhz += "${bindir}/setopp4.sh"
FILES_${PN}-1ghz += "${bindir}/setopp1.sh"

SRC_URI[md5sum] = "cb225b9f1c24a4f13cb1a127d91be060"
SRC_URI[sha256sum] = "8b5ece45f975d213c963471f8d3ec9d2ab8244f44a4420bb99f408488767dc0c"
