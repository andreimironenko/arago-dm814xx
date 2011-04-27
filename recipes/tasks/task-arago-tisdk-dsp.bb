DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r14"
LICENSE="MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_DVSDK_COMMON = " \
    ti-dsplink-examples \
"

DSP_EZSDK_COMMON = " \
    ti-syslink-examples \
    ti-c6accel-apps \
    ti-c6run-apps \
    ti-dsp-benchmark-apps \
    ti-integra-demos \
    ti-uia \
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

DSP_COMPONENTS_c6a816x = "\
    ${DSP_EZSDK_COMMON} \
    ti-hdvpss-display-utils \
    ti-hdvpss-display-utils-loader \
    "

DSP_COMPONENTS_dm816x = "\
    ${DSP_EZSDK_COMMON} \
    ti-media-controller-utils \
    ti-media-controller-hdvpss-loader \
    ti-media-controller-hdvicp2-loader \
    "

DSP_COMPONENTS_c6a814x = "\
    ${DSP_EZSDK_COMMON} \
    ti-hdvpss-display-utils \
    ti-hdvpss-display-utils-loader \
    "

DSP_COMPONENTS_dm814x = "\
    ${DSP_EZSDK_COMMON} \
    ti-media-controller-utils \
    ti-media-controller-hdvpss-loader \
    ti-media-controller-hdvicp2-loader \
    "

DSP_COMPONENTS_am37x-evm = ""

DSP_COMPONENTS = ""

RDEPENDS = "\
    ${DSP_COMPONENTS} \
    "
