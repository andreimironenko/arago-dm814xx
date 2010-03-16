DESCRIPTION = "Task to build and install Board Support package sources (or development header) packages on host"
PR = "r2"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS = "\
    ti-linux-driver-examples-sourcetree \
    "

RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    "
