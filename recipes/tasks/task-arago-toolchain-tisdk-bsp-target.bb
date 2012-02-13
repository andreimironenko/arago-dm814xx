DESCRIPTION = "Task to build and install corresponding development packages for Board Support Package"
PR = "r6"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

inherit task

BLUETOOTH_STACK = "\
    bluez4-dev \
    openobex-dev \
    "

BSP_COMPONENTS = ""
BSP_COMPONENTS_ti33x = "canutils-dev"

# Bluetooth development packages
BLUETOOTH_SUPPORT = ""

BLUETOOTH_SUPPORT_omap3 = "${BLUETOOTH_STACK}"
BLUETOOTH_SUPPORT_omapl138 = "${BLUETOOTH_STACK}"
BLUETOOTH_SUPPORT_ti816x = "${BLUETOOTH_STACK}"
BLUETOOTH_SUPPORT_ti814x = "${BLUETOOTH_STACK}"
BLUETOOTH_SUPPORT_omap4 = "${BLUETOOTH_STACK}"

RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    ${BLUETOOTH_SUPPORT} \
    "
