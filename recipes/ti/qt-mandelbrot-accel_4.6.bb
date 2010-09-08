DESCRIPTION = "Qt Embedded TI DSP Fractal Demo"
LICENSE = " "

require ti-paths.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

SRC_URI = "file://mandelbrot-v${PV}.tar.gz \
          file://qt-mandelbrot-accel.patch "

S = "${WORKDIR}/mandelbrot"

DEPENDS = "ti-c6run "

do_buildc6runlib() {
    cd ${S}
    ${C6RUN_INSTALL_DIR}/bin/c6runlib-cc -O3 -c -ms0 critical.c
    ${C6RUN_INSTALL_DIR}/bin/c6runlib-ar rcs critical.lib critical.o
}

addtask buildc6runlib before do_compile


inherit qt4e

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/mandelbrot ${D}/${bindir}/mandelbrot_ti
}

RRECOMMENDS_${PN} = "qt4-embedded-plugin-mousedriver-tslib"

