DESCRIPTION = "Task to build and install Board Support package sources (or development header) packages on host"
PR = "r3"
LICENSE="MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS = "\
    ti-linux-driver-examples-sourcetree \
    "
RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    "

RRECOMMENDS_${PN}_da830-omapl137-evm = ""

