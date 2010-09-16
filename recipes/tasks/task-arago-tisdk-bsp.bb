DESCRIPTION = "Task to install Board Support Package binaries on ${MACHINE}"
PR = "r17"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS = ""

BSP_COMPONENTS_dm355 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_dm365 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_dm6446 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_dm6467 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_da850-omapl138-evm = "\
    ti-linux-driver-examples-omapl \
    "

BSP_COMPONENTS_omap3 = "\
    x-load \
    "

BSP_COMPONENTS_append_omap3evm = "\
    ti-linux-driver-examples-omap3 \
    "    

BSP_COMPONENTS_append_dm37x-evm = "\
    ti-linux-driver-examples-omap3 \
    "    

BLUETOOTH_SUPPORT = ""

BLUETOOTH_SUPPORT_omap3 = "\
    bluez4 \
    bluez4-agent \
    libasound-module-bluez \
    bluez-hcidump \
    openobex \
    openobex-apps \
    obexftp \
    ussp-push \
    "

RDEPENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    ${BLUETOOTH_SUPPORT} \
    "

RRECOMMENDS_${PN} = "\
    kernel-modules \
    kernel-image \
    kernel-vmlinux \
    u-boot \
    "

