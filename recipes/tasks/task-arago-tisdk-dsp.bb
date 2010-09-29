DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r12"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_COMPONENTS_dm6446 = "\
    ti-dsplink-examples \
    "

DSP_COMPONENTS_dm6467 = "\
    ti-dsplink-examples \
    "

DSP_COMPONENTS_da830-omapl137-evm = "\
    ti-dsplink-examples \
    "

DSP_COMPONENTS_da850-omapl138-evm = "\
    ti-dsplink-examples \
    ti-c6accel-apps \
    ti-c6run-apps \
    qt-mandelbrot-accel \
    ti-dsp-benchmark-apps \
    "

DSP_COMPONENTS_omap3 = "\
    ti-dsplink-examples \
    ti-c6accel-apps \
    ti-c6run-apps \
    "

DSP_COMPONENTS = ""

RDEPENDS = "\
    ${DSP_COMPONENTS} \
	"
