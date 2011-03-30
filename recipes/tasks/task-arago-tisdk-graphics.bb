DESCRIPTION = "Task to install graphics binaries on ${MACHINE}"
PR = "r12"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

# for now install qt-widget demo on omapl138
QTWIDGET_DEMO = ""
QTWIDGET_DEMO_omapl138 = "qt-embedded-widgets-demo"

# Make the default to not include matrix-gui since this is a machine
# specific component and often times a new machine type will be added
# before matrix is updated to support it.
MATRIX_APPS = ""
MATRIX_APPS_omapl138 = "matrix-gui-e"
MATRIX_APPS_omap3 = "matrix-gui-e"
MATRIX_APPS_ti816x = "matrix-gui-e"
MATRIX_APPS_dm365 = "matrix-gui-e"

# Install 3D graphics for all omap3 SOC_FAMILY devices
GRAPHICS_3D = ""
GRAPHICS_3D_omap3 = "libgles-omap3-rawdemos"

RDEPENDS_${PN} = "\
    task-arago-qte \
    qt4-embedded-plugin-phonon-backend-gstreamer \
    qt4-embedded-plugin-imageformat-gif \
    qt4-embedded-plugin-imageformat-jpeg \
    libxml2 \
    ${MATRIX_APPS} \
    ${QTWIDGET_DEMO} \
    ${GRAPHICS_3D} \
    "
