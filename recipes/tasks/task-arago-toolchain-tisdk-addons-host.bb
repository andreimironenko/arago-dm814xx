DESCRIPTION = "Task to install sources for additional utilities/demos for SDKs"
PR = "r10"
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
UTILS_append_ti816x = " am-benchmarks-src"
UTILS_append_dm365 = " am-benchmarks-src"


# Add pru and profibus sources for omapl138 devices
# Comment out PRU and Profibus support until the code is migrated
# to 2.6.37 kernel.
#UTILS_omapl138 = " \
#    ti-pru-sw-examples-src \
#    "

# Add matrix-tui sources for AM devices
UTILS_append_am37x-evm = " matrix-tui-src"
UTILS_append_am3517-evm = " matrix-tui-src"
UTILS_append_am180x-evm = " matrix-tui-src"
UTILS_append_am181x-evm = " matrix-tui-src"

UTILS_ti814x = " \ 
  "

RDEPENDS_${PN} = "\
    ${UTILS_COMMON} \
    ${UTILS} \
    "
