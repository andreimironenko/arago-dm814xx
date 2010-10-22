DESCRIPTION = "Task to install graphics binaries on ${MACHINE}"
PR = "r10"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

# for now install qt-widget demo on omapl138
QTWIDGET_DEMO = ""
QTWIDGET_DEMO_omapl138 = "qt-embedded-widgets-demo"

FFT_DEMO = ""
FFT_DEMO_c6a816x-evm = "\
    qwt \
    "

MATRIX_APPS = "\
    libxml2 \
    matrix-gui-e \
    ${QTWIDGET_DEMO} \
    ${FFT_DEMO} \
    "

# Install 3D graphics for all omap3 SOC_FAMILY devices
GRAPHICS_3D = ""
GRAPHICS_3D_omap3 = "libgles-omap3-rawdemos"

RDEPENDS_${PN} = "\
    task-arago-qte \
    qt4-embedded-plugin-phonon-backend-gstreamer \
    ${MATRIX_APPS} \
    ${GRAPHICS_3D} \
    "
