DESCRIPTION = "Task to build and install dsp binaries on sdk target"
PR = "r1"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_SDK = ""

DSP_EZSDK_COMMON = " \
    ti-syslink-dev \
"

DSP_SDK_ti816x = "\
    ${DSP_EZSDK_COMMON} \
    "

DSP_SDK_ti814x = "\
    ${DSP_EZSDK_COMMON} \
    "

RRECOMMENDS_${PN} = "\
    ${DSP_SDK} \
    "

