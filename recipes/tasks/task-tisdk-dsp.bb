DESCRIPTION = "Task to install C64x binaries on ${MACHINE}"
PR = "r1"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_COMPONENTS = "\
    ti-dsplink-examples \
    "

RRECOMMENDS_${PN} = "\
    ${DSP_COMPONENTS} \
    "
