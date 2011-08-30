DESCRIPTION = "Task to install graphics application sources on host"
PR = "r8"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

GRAPHICS_APPS = " \
    matrix-gui-src \
    "

RDEPENDS_${PN} = "\
    ${GRAPHICS_APPS} \
    "
