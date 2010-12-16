DESCRIPTION = "Task to install sources for additional utilities/demos for SDKs"
PR = "r6"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = ""

UTILS_COMMON = " \
    matrix-tui-src \
    am-sysinfo-src \
    am-benchmarks-src \
    "

# Add pru and profibus sources for omapl138 devices
UTILS_omapl138 = " \
    ti-pru-sw-examples-src \
    "

RDEPENDS_${PN} = "\
    ${UTILS_COMMON} \
    ${UTILS} \
    "
