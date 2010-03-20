DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r3"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_COMPONENTS_dm6446 = "\
    ti-dsplink-examples \
    "

DSP_COMPONENTS_da830-omapl137-evm = "\
    ti-dsplink-examples \
    "

RDEPENDS = "\
    ${DSP_COMPONENTS} \
	"
