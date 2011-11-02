DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r18"
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
    ti-integra-demos \
    ti-dsp-benchmark-apps \
    ti-uia \
    ti-media-controller-utils \
    ti-media-controller-loader \
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
    ${DSP_EZSDK_COMMON} \
    ti-uia \
    ti-hdvpss-display-utils \
    ti-hdvpss-display-utils-loader \
    "

DSP_COMPONENTS_dm816x-evm = "\
    ${DSP_EZSDK_COMMON} \
    ti-firmware \
    "

DSP_COMPONENTS_c6a814x-evm = "\
    ${DSP_EZSDK_COMMON} \
    ti-hdvpss-display-utils \
    ti-hdvpss-display-utils-loader \
    "

DSP_COMPONENTS_dm814x-evm = "\
    ${DSP_EZSDK_COMMON} \
    ti-firmware \
    "

# For AM devices with no DSP we want to not build any DSP packages
# if this task is built.
DSP_COMPONENTS_am180x-evm = ""
DSP_COMPONENTS_am181x-evm = ""
DSP_COMPONENTS_am37x-evm = ""
DSP_COMPONENTS_beagleboard = ""
DSP_COMPONENTS_am3517-evm = ""
DSP_COMPONENTS_am389x-evm = ""
DSP_COMPONENTS_am387x-evm = ""
DSP_COMPONENTS_am45x-evm = ""
DSP_COMPONENTS_am335x-evm = ""
DSP_COMPONENTS_am3359-evm = ""

DSP_COMPONENTS = ""

RDEPENDS = "\
    ${DSP_COMPONENTS} \
    "
