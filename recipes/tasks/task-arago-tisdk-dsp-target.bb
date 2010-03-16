DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r1"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_COMPONENTS = "\
    ti-dsplink-examples \
    "
RDEPENDS = "\
    ${DSP_COMPONENTS} \
	"
