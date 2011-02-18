DESCRIPTION = "Task to install graphics application sources on host"
PR = "r8"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

GRAPHICS_APPS = ""

GRAPHICS_APPS_omap3 = " \
    matrix-gui-e-src \
    libgles-omap3-src \   
    "
   
GRAPHICS_APPS_ti816x = " \
    libgles-omap3-src \
    matrix-gui-e-src \
    "

GRAPHICS_APPS_ti814x = " \
    libgles-omap3-src \
    matrix-gui-e-src \
    "

GRAPHICS_APPS_omapl138 = " \
    matrix-gui-e-src \
    "

GRAPHICS_APPS_dm365 = " \
    matrix-gui-e-src \
    "

RDEPENDS_${PN} = "\
    ${GRAPHICS_APPS} \
    "
