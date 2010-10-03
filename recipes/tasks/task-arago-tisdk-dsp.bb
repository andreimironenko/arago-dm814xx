DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r12"
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

DSP_COMPONENTS_da830-omapl137-evm = "\
    ${DSP_DVSDK_COMMON} \
    "

DSP_COMPONENTS_da850-omapl138-evm = "\
    ${DSP_DVSDK_COMMON} \
    ti-c6accel-apps \
    ti-dsp-benchmark-apps \
    "

DSP_COMPONENTS_omap3 = "\
    ${DSP_DVSDK_COMMON} \
    ti-c6accel-apps \
    "

DSP_COMPONENTS = ""

RDEPENDS = "\
    ${DSP_COMPONENTS} \
	"
