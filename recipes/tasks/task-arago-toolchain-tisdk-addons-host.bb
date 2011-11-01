DESCRIPTION = "Task to install sources for additional utilities/demos for SDKs"
PR = "r15"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = ""

UTILS_COMMON = " \
    am-sysinfo-src \
    "

# Add am-benchmarks sources for supported devices
UTILS_append_omapl138 = " am-benchmarks-src"
UTILS_append_omap3 = " am-benchmarks-src"
UTILS_append_ti33x = " am-benchmarks-src"
UTILS_append_ti816x = " am-benchmarks-src"
UTILS_append_ti814x = " am-benchmarks-src"
UTILS_append_dm365 = " am-benchmarks-src"


# Add pru and profibus sources for omapl138 devices
UTILS_append_omapl138 = " \
    ti-pru-sw-examples-src \
    "

RDEPENDS_${PN} = "\
    ${UTILS_COMMON} \
    ${UTILS} \
    "
