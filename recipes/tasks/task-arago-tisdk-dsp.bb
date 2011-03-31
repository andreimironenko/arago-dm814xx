DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r15"
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
# For AM devices with no DSP we want to not build any DSP packages
# if this task is built.
DSP_COMPONENTS_am180x-evm = ""
DSP_COMPONENTS_am181x-evm = ""
DSP_COMPONENTS_am37x-evm = ""
DSP_COMPONENTS_am3517-evm = ""
DSP_COMPONENTS_am389x-evm = ""
DSP_COMPONENTS_am387x-evm = ""

DSP_COMPONENTS = ""

RDEPENDS = "\
    ${DSP_COMPONENTS} \
    "
