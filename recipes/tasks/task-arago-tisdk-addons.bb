DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r4"
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

RDEPENDS_${PN} = "\
    ${UTILS} \
    "
