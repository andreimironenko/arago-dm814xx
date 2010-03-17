DESCRIPTION = "Task to install dsp binaries on ${MACHINE}"
PR = "r2"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

DSP_COMPONENTS_dm6446 = "\
    ti-dsplink-examples \
    "
RDEPENDS = "\
    ${DSP_COMPONENTS} \
	"
