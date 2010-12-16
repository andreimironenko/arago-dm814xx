DESCRIPTION = "Task to install crypto sources in SDK"
PR = "ro"
LICENSE = "MIT"

inherit task

PACKAGE_ARCH = ${MACHINE_ARCH}

# Default WLAN value is empty set
WLAN = ""

# Add WLAN sources for am180x-evm
WLAN_am180x-evm = "\
    wl1271-wlan-cli-src \
    "

RDEPENDS_${PN} = "\
    ${WLAN} \
    "
