DESCRIPTION = "Task to build and install source (or development) packages on host"
PR = "r8"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_SOURCETREE_dm6446 = "\
    ti-cgt6x-sourcetree \
    ti-dspbios-sourcetree \
    ti-biosutils-sourcetree \
    ti-dsplink-sourcetree \
    ti-edma3lld-sourcetree \
    "

DSP_SOURCETREE_dm6467 = "\
    ti-cgt6x-sourcetree \
    ti-dspbios-sourcetree \
    ti-biosutils-sourcetree \
    ti-dsplink-sourcetree \
    ti-edma3lld-sourcetree \
    "

DSP_SOURCETREE_da830-omapl137-evm = "\
    ti-cgt6x-sourcetree \
    ti-dspbios-sourcetree \
    ti-biosutils-sourcetree \
    ti-dsplink-sourcetree \
    ti-edma3lld-sourcetree \
    ti-biospsp-sourcetree \
    ti-audio-soc-example-sourcetree \
    "

DSP_SOURCETREE_da850-omapl138-evm = "\
    ti-cgt6x-sourcetree \
    ti-dspbios-sourcetree \
    ti-biosutils-sourcetree \
    ti-dsplink-sourcetree \
    ti-edma3lld-sourcetree \
    ti-biospsp-sourcetree \
    ti-audio-soc-example-sourcetree \
    ti-c6accel-sourcetree \
    "

DSP_SOURCETREE_omap3 = "\
    ti-cgt6x-sourcetree \
    ti-dspbios-sourcetree \
    ti-biosutils-sourcetree \
    ti-dsplink-sourcetree \
    ti-edma3lld-sourcetree \
    ti-c6accel-sourcetree \
    "
DSP_SOURCETREE = " "

RRECOMMENDS_${PN} = "\
    ${DSP_SOURCETREE} \
    "

