DESCRIPTION = "Task to install graphics binaries on ${MACHINE}"
PR = "r20"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

# for now install qt-widget demo on omapl138
QTWIDGET_DEMO = ""
QTWIDGET_DEMO_omapl138 = "qt-embedded-widgets-demo"

MATRIX_APPS_COMMON = "matrix-gui-coming-soon \
                      matrix-gui-crypto-demos \
                      matrix-gui-qt4-demos \
                      "

MATRIX_APPS = ""
MATRIX_APPS_omap3 = "matrix-gui-clocks-300mhz \
                     matrix-gui-clocks-600mhz \
                     matrix-gui-clocks-800mhz \
                     matrix-gui-clocks-1ghz \
                     matrix-gui-wifi-demos \
                     matrix-gui-3d-demos \
                     matrix-gui-display-control \
                     matrix-gui-pm-demos \
                     "

# For now add some explicit submenus to get a consistent look and feel
MATRIX_APPS_ti33x = "matrix-gui-3d-demos \
                     matrix-gui-submenus-wifi \
                     matrix-gui-submenus-multimedia \
                     matrix-gui-submenus-usb \
                     matrix-gui-submenus-ethernet \
                     matrix-gui-submenus-power \
                     matrix-gui-submenus-pru \
                     matrix-gui-submenus-oprofile \
                     "

# Install 3D graphics for all omap3 SOC_FAMILY devices
GRAPHICS_3D = ""
GRAPHICS_3D_omap3 = "libgles-omap3-rawdemos"

RDEPENDS_${PN} = "\
    task-arago-qte \
    qt4-embedded-plugin-phonon-backend-gstreamer \
    qt4-embedded-plugin-imageformat-gif \
    qt4-embedded-plugin-imageformat-jpeg \
    libxml2 \
    ${MATRIX_APPS_COMMON} \
    ${MATRIX_APPS} \
    ${QTWIDGET_DEMO} \
    ${GRAPHICS_3D} \
    "
