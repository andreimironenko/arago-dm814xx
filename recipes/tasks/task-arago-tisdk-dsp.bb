DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r13"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_DVSDK_COMMON = " \
    ti-dsplink-examples \
"

DSP_COMPONENTS_dm6446 = "\
    ${DSP_DVSDK_COMMON} \
    "

DSP_COMPONENTS_dm6467 = "\
    ${DSP_DVSDK_COMMON} \
    "

DSP_COMPONENTS_omapl137 = "\
    ${DSP_DVSDK_COMMON} \
    "

DSP_COMPONENTS_omapl138 = "\
    ${DSP_DVSDK_COMMON} \
    ti-c6accel-apps \
    ti-dsp-benchmark-apps \
    ti-c6run-apps \
    qt-mandelbrot-accel \
    "

DSP_COMPONENTS_omap3 = "\
    ${DSP_DVSDK_COMMON} \
    ti-c6accel-apps \
    ti-c6run-apps \
    "

DSP_COMPONENTS_c6a816x-evm = "\
    ti-syslink-examples \
    ti-c6accel-apps \
    ti-hdvpss-display-utils \
    ti-hdvpss-display-utils-loader \
    ti-hdvpss-display-utils-module \
    ti-integra-demos \
    "

DSP_COMPONENTS_dm816x-evm = "\
    ti-syslink-examples \
    ti-c6accel-apps \
    ti-integra-demos \
    "

DSP_COMPONENTS = ""

RDEPENDS = "\
    ${DSP_COMPONENTS} \
	"

