DESCRIPTION = "Task to install graphics binaries on ${MACHINE}"
PR = "r27"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

# for now install qt-widget demo on omapl138
QTWIDGET_DEMO = ""
QTWIDGET_DEMO_omapl138 = "qt-embedded-widgets-demo"

MATRIX_APPS = "matrix-gui \
               matrix-gui-crypto-demos \
               matrix-gui-qt4-demos \
               matrix-gui-usb-demos \
               matrix-gui-submenus-oprofile \
               matrix-gui-submenus-ethernet \
              "

MATRIX_APPS_append_omap3 = "matrix-gui-3d-demos \
                            matrix-gui-display-control \
                           "

MATRIX_APPS_append_am37x-evm = "matrix-gui-clocks-300mhz \
                                matrix-gui-clocks-600mhz \
                                matrix-gui-clocks-800mhz \
                                matrix-gui-clocks-1ghz \
                                matrix-gui-wifi-demos \
                                matrix-gui-pm-demos \
                               "

MATRIX_APPS_append_am180x-evm = "matrix-gui-wifi-demos"

# For now add some explicit submenus to get a consistent look and feel
MATRIX_APPS_append_ti33x = "matrix-gui-3d-demos \
                            matrix-gui-wifi-demos \
                            matrix-gui-submenus-multimedia \
                            matrix-gui-submenus-power \
                           "

RDEPENDS_${PN} = "\
    task-arago-qte \
    qt4-embedded-plugin-phonon-backend-gstreamer \
    qt4-embedded-plugin-imageformat-gif \
    qt4-embedded-plugin-imageformat-jpeg \
    libxml2 \
    ${MATRIX_APPS} \
    ${QTWIDGET_DEMO} \
    "
