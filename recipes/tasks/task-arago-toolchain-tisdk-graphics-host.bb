DESCRIPTION = "Task to install graphics application sources on host"
PR = "r6"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

GRAPHICS_APPS = ""

GRAPHICS_APPS_dm37x-evm = "\
    libgles-omap3-src \
    matrix-gui-src \
    "

GRAPHICS_APPS_omap3evm = "\
    libgles-omap3-src \
    matrix-gui-src \
    "

GRAPHICS_APPS_am37x-evm = " \
    matrix-gui-e-src \
    "

GRAPHICS_APPS_da850-omapl138-evm = " \
    matrix-gui-src \
    "

GRAPHICS_APPS_dm365-evm = " \
    matrix-gui-src \
    "

RDEPENDS_${PN} = "\
    ${GRAPHICS_APPS} \
    "
