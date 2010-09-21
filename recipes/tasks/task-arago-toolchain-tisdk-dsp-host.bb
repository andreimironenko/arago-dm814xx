DESCRIPTION = "Task to build and install source (or development) packages on host"
PR = "r10"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_SOURCETREE_dm6446 = "\
    ti-cgt6x-src \
    ti-dspbios-src \
    ti-biosutils-src \
    ti-dsplink-src \
    ti-edma3lld-src \
    "

DSP_SOURCETREE_dm6467 = "\
    ti-cgt6x-src \
    ti-dspbios-src \
    ti-biosutils-src \
    ti-dsplink-src \
    ti-edma3lld-src \
    "

DSP_SOURCETREE_da830-omapl137-evm = "\
    ti-cgt6x-src \
    ti-dspbios-src \
    ti-biosutils-src \
    ti-dsplink-src \
    ti-edma3lld-src \
    ti-biospsp-src \
    ti-audio-soc-example-src \
    "

DSP_SOURCETREE_da850-omapl138-evm = "\
    ti-cgt6x-src \
    ti-dspbios-src \
    ti-biosutils-src \
    ti-dsplink-src \
    ti-edma3lld-src \
    ti-biospsp-src \
    ti-audio-soc-example-src \
    ti-c6accel-src \
    ti-c6run-src \
    "

DSP_SOURCETREE_omap3 = "\
    ti-cgt6x-src \
    ti-dspbios-src \
    ti-biosutils-src \
    ti-dsplink-src \
    ti-edma3lld-src \
    ti-c6accel-src \
    ti-c6run-src \
    "

DSP_SOURCETREE_c6a816x-evm = "\
    ti-cgt6x-src \
    ti-sysbios-src \
    ti-xdctools-src \
    ti-framework-components-src \
    ti-linuxutils-src \
    ti-xdais-src \
    ti-osal-src \
    "

DSP_SOURCETREE = " "

RRECOMMENDS_${PN} = "\
    ${DSP_SOURCETREE} \
    "

