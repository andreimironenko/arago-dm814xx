DESCRIPTION = "Task to install crypto sources in SDK"
PR = "r5"
LICENSE = "MIT"

inherit task

PACKAGE_ARCH = ${MACHINE_ARCH}

# Default CRYPTO value is empty set
CRYPTO = ""

# Add OCF driver for am37x-evm
CRYPTO_am37x-evm = "\
    ti-ocf-crypto-module-src \
    "

# Add OCF driver for am3517-evm
CRYPTO_am3517-evm = "\
    ti-ocf-crypto-module-src \
    "

# Default WLAN value is empty set
WLAN = ""

RDEPENDS_${PN} = "\
    ${WLAN} \
    ${CRYPTO} \
    "
