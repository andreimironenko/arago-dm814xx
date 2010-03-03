DESCRIPTION = "Task to build and install packages needed for TISDK"
PR = "r1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_COMPONENTS = "\
    ti-cgt6x-sourcetree \
    ti-dspbios-sourcetree \
    ti-biosutils-sourcetree \
    ti-dsplink-sourcetree \
    ti-edma3lld-sourcetree \
    "

RRECOMMENDS_${PN} = "\
    ${DSP_COMPONENTS} \
    "
