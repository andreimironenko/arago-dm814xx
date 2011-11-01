DESCRIPTION = "Task to build and install source (or development) packages on host"
PR = "r17"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_DVSDK_COMMON = " \
    ti-cgt6x-src \
    ti-dspbios-src \
    ti-biosutils-src \
    ti-dsplink-src \
    ti-edma3lld-src \
"

DSP_EZSDK_COMMON = " \
    ti-cgt6x-src \
    ti-sysbios-src \
    ti-xdctools-src \
    ti-edma3lld-src \
    ti-framework-components-src \
    ti-linuxutils-src \
    ti-xdais-src \
    ti-osal-src \
    ti-ipc-src \
    ti-syslink-src \
    ti-codec-engine-src \
    ti-c6accel-src \
    ti-c6run-src \
    ti-dsplib-c674x-src \
    ti-imglib-c64plus-src \
    ti-mathlib-c67x-src \
    ti-signal-analyzer-demo-src \
    ti-signal-analyzer-demo-doc \
    ti-docs-faqs \
    ti-dsp-benchmark-src \
    ti-media-controller-utils-src \
    ti-docs-dsp-devkit \
"

DSP_SOURCETREE_dm6446 = "\
    ${DSP_DVSDK_COMMON} \
    "

DSP_SOURCETREE_dm6467 = "\
    ${DSP_DVSDK_COMMON} \
    "

DSP_SOURCETREE_omapl137 = "\
    ${DSP_DVSDK_COMMON} \
    ti-biospsp-src \
    ti-audio-soc-example-src \
    "

DSP_SOURCETREE_omapl138 = "\
    ${DSP_DVSDK_COMMON} \
    ti-biospsp-src \
    ti-audio-soc-example-src \
    ti-c6accel-src \
    ti-dsp-benchmark-src \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-xdais-src \
    ti-c6run-src \
    ti-dsp-benchmark-src \
    "

DSP_SOURCETREE_omap3 = "\
    ${DSP_DVSDK_COMMON} \
    ti-c6accel-src \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-xdais-src \
    ti-c6run-src \
    "

DSP_SOURCETREE_c6a816x-evm = "\
    ${DSP_EZSDK_COMMON} \
    "

DSP_SOURCETREE_dm816x-evm = "\
    ${DSP_EZSDK_COMMON} \
    ti-firmware-dm816x-src \
    "

DSP_SOURCETREE_dm816x-custom = "\
    ${DSP_EZSDK_COMMON} \
    "

DSP_SOURCETREE_dm814x-evm = "\
    ${DSP_EZSDK_COMMON} \
    ti-firmware-dm814x-src \
    "

DSP_SOURCETREE = " "

# For AM devices with no DSP we want to not build any DSP packages
# if this task is built.
DSP_SOURCETREE_am180x-evm = ""
DSP_SOURCETREE_am181x-evm = ""
DSP_SOURCETREE_am37x-evm = ""
DSP_SOURCETREE_beagleboard = ""
DSP_SOURCETREE_am3517-evm = ""
DSP_SOURCETREE_am389x-evm = ""
DSP_SOURCETREE_am387x-evm = ""
DSP_SOURCETREE_am45x-evm = ""
DSP_SOURCETREE_am335x-evm = ""
DSP_SOURCETREE_am3359-evm = ""

RRECOMMENDS_${PN} = "\
    ${DSP_SOURCETREE} \
    "

