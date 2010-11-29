DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r6"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = ""

UTILS_ti816x = " \
    matrix-tui \
    am-sysinfo \
    am-benchmarks \
    "

UTILS_omap3 = " \
    am-sysinfo \
    am-benchmarks \
    "

UTILS_dm365 = " \
    am-sysinfo \
    am-benchmarks \
    "

UTILS_omapl138 = " \
    am-sysinfo \
    am-benchmarks \
    "

RDEPENDS_${PN} = "\
    ${UTILS} \
    "
