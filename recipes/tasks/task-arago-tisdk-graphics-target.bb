DESCRIPTION = "Task to install graphics binaries on ${MACHINE}"
PR = "r3"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

MATRIX_APPS = " libxml2 \
 matrix-gui \
 am-sysinfo \
 am-benchmarks \
"

RDEPENDS_${PN} = "\
    qt4-embedded \
    qt4-embedded-plugin-phonon-backend-gstreamer \
    qt4-embedded-plugin-mousedriver-tslib \
    qt4-embedded-plugin-gfxdriver-gfxtransformed \    
    ${MATRIX_APPS} \
    "

