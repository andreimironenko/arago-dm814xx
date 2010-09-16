DESCRIPTION = "Task to install Board Support Package binaries on ${MACHINE}"
PR = "r18"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS = ""

PRIMARY_BOOTLOADER_omap3 = "u-boot-omap3"
PRIMARY_BOOTLOADER_dm365 = "u-boot-davinci"
PRIMARY_BOOTLOADER_omapl138 = "u-boot-omapl1"
PRIMARY_BOOTLOADER ?= "u-boot"

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
    ${PRIMARY_BOOTLOADER} \
    "

