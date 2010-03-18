DESCRIPTION = "Task to install Board Support Package binaries on $MACHINE"
PR = "r3"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS = "\
    ti-linux-driver-examples \
    "

RDEPENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    "

RRECOMMENDS_${PN} = "\
    kernel-modules \
    kernel-image \
    kernel-vmlinux \
	"

