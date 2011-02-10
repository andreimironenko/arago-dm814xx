DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r8"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = " \
    am-sysinfo \
    am-benchmarks \
    "

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
