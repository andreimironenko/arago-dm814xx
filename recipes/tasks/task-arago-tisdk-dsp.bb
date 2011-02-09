DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r14"
LICENSE="MIT"
ALLOW_EMPTY = "1"

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
    "

DSP_COMPONENTS_omap3 = "\
    ${DSP_DVSDK_COMMON} \
    ti-c6accel-apps \
    "

DSP_COMPONENTS_am37x-evm = ""

DSP_COMPONENTS = ""

RDEPENDS = "\
    ${DSP_COMPONENTS} \
    "
