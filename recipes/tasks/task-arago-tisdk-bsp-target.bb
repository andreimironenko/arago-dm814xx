DESCRIPTION = "Task to install Board Support Package binaries on $MACHINE"
PR = "r4"
LICENSE="MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS = ""

BSP_COMPONENTS_dm355 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_dm365 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_dm6446 = "\
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

