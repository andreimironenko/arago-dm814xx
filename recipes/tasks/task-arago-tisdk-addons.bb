DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r4"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = ""

UTILS_ti816x = " \
    matrix-tui \
    am-sysinfo \
    am-benchmarks \
    "

UTILS_am37x-evm = " \
    am-sysinfo \
    am-benchmarks \
    "

UTILS_omap3evm = " \
    am-sysinfo \
    am-benchmarks \
    "

UTILS_dm365-evm = " \
    am-sysinfo \
    am-benchmarks \
    "

UTILS_da850-omapl138-evm = " \
    am-sysinfo \
    am-benchmarks \
    "

RDEPENDS_${PN} = "\
    ${UTILS} \
    "
