DESCRIPTION = "Task to install crypto sources in SDK"
PR = "r2"
LICENSE = "MIT"

inherit task

PACKAGE_ARCH = ${MACHINE_ARCH}

# Default CRYPTO value is empty set
CRYPTO = ""

# Add OCF driver for am37x-evm
# Comment out OCF until we have an implementation for 2.6.37
#CRYPTO_am37x-evm = "\
#    ti-ocf-crypto-module-src \
#    "

# Default WLAN value is empty set
WLAN = ""

# Add WLAN sources for am180x-evm
WLAN_am180x-evm = "\
    wl1271-wlan-cli-src \
    "

RDEPENDS_${PN} = "\
    ${WLAN} \
    ${CRYPTO} \
    "
