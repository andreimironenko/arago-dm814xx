DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r10"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = " \
    am-sysinfo \
    "

UTILS_dm6467 = ""

# Add am-benchmarks for supported devices.
UTILS_append_omapl138 = " am-benchmarks"
UTILS_append_omap3 = " am-benchmarks"
UTILS_append_ti816x = " am-benchmarks"
UTILS_append_dm365 = " am-benchmarks"

# Add PRU examples for am180x-evm devices
UTILS_append_am180x-evm = " ti-pru-sw-examples"

# Add Profibus software for am181x-evm devices
UTILS_append_am181x-evm = " ti-profibus-slave-sw-app"

# Add matrix-tui for AM devices
UTILS_append_am37x-evm = " matrix-tui"
UTILS_append_am180x-evm = " matrix-tui"
UTILS_append_am181x-evm = " matrix-tui"
UTILS_append_am3517-evm = " matrix-tui"

RDEPENDS_${PN} = "\
    ${UTILS} \
    "
