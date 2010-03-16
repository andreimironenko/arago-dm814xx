DESCRIPTION = "Task to build and install source (or development) packages on host"
PR = "r1"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_SOURCETREE = "\
    ti-cgt6x-sourcetree \
    ti-dspbios-sourcetree \
    ti-biosutils-sourcetree \
    ti-dsplink-sourcetree \
    ti-edma3lld-sourcetree \
    "

RRECOMMENDS_${PN} = "\
    ${DSP_SOURCETREE} \
    "
