DESCRIPTION = "Task to install Board Support Package binaries on $MACHINE"
PR = "r1"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS = "\
    ti-linux-driver-examples \
    kernel-modules \
    kernel-image \
    "

RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    "
