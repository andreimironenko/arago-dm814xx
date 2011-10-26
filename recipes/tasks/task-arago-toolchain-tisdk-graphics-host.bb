DESCRIPTION = "Task to install graphics application sources on host"
PR = "r9"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

GRAPHICS_APPS = " \
    matrix-gui-src \
    matrix-gui-browser-src \
    "

RDEPENDS_${PN} = "\
    ${GRAPHICS_APPS} \
    "
