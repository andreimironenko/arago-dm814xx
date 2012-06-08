DESCRIPTION = "Task to install additional utilities/demos for SDKs"
PR = "r19"
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
UTILS_append_ti33x = " am-benchmarks"
UTILS_append_ti816x = " am-benchmarks"
UTILS_append_ti814x = " am-benchmarks"
UTILS_append_ti811x = " am-benchmarks"
UTILS_append_dm365 = " am-benchmarks"

# Add PRU examples for am180x-evm devices
UTILS_append_am180x-evm = " ti-pru-sw-examples"

# Add Profibus software for am181x-evm devices
#UTILS_append_am181x-evm = " ti-profibus-slave-sw-app"

AM_ADDONS = " \
    dt \
    task-arago-test \
    gdbserver \
    oprofile \
    "

# Add additional packages for AM devices
UTILS_append_am37x-evm = " ${AM_ADDONS}"
UTILS_append_beagleboard = " ${AM_ADDONS}"
UTILS_append_am180x-evm = " ${AM_ADDONS}"
UTILS_append_am181x-evm = " ${AM_ADDONS}"
UTILS_append_am3517-evm = " ${AM_ADDONS}"
UTILS_append_am335x-evm = " ${AM_ADDONS}"

RDEPENDS_${PN} = "\
    ${UTILS} \
    "
