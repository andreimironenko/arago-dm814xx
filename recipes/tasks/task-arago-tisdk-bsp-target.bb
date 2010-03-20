DESCRIPTION = "Task to install Board Support Package binaries on $MACHINE"
PR = "r4"
LICENSE="MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS = "\
    ti-linux-driver-examples \
    "

RDEPENDS_${PN}_da830-omapl137-evm = ""

RDEPENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    "

RRECOMMENDS_${PN} = "\
    kernel-modules \
    kernel-image \
    kernel-vmlinux \
	"

