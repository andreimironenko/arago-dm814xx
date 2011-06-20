DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r13"
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

# Comment out PRU and Profibus support until the code is migrated
# to 2.6.37 kernel.
# Add PRU examples for am180x-evm devices
#UTILS_append_am180x-evm = " ti-pru-sw-examples"

# Add Profibus software for am181x-evm devices
#UTILS_append_am181x-evm = " ti-profibus-slave-sw-app"

AM_ADDONS = " \
    matrix-tui \
    oprofile \
    dt \
    task-arago-test \
    "

# Add matrix-tui for AM devices
UTILS_append_am37x-evm = " ${AM_ADDONS}"
UTILS_append_beagleboard = " ${AM_ADDONS}"
UTILS_append_am180x-evm = " ${AM_ADDONS}"
UTILS_append_am181x-evm = " ${AM_ADDONS}"
UTILS_append_am3517-evm = " ${AM_ADDONS}"

RDEPENDS_${PN} = "\
    ${UTILS} \
    "
