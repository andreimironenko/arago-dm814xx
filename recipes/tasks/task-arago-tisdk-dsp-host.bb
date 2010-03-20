DESCRIPTION = "Task to build and install source (or development) packages on host"
PR = "r3"
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

DSP_SOURCETREE_da830-omapl137-evm = "\
    ti-cgt6x-sourcetree \
    ti-dspbios-sourcetree \
    ti-biosutils-sourcetree \
    ti-dsplink-sourcetree \
    ti-edma3lld-sourcetree \
    "

RRECOMMENDS_${PN} = "\
    ${DSP_SOURCETREE} \
    "
