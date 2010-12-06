DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r5"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = " \
    matrix-tui \
    am-sysinfo \
    am-benchmarks \
    "

# Add PRU examples for am180x-evm devices
UTILS_am180x-evm += "ti-pru-sw-examples"

# Add Profibus software for am181x-evm devices
UTILS_am181x-evm += "ti-profibus-slave-sw-app"

RDEPENDS_${PN} = "\
    ${UTILS} \
    "
