DESCRIPTION = "Additional packages beyond console packages shared by TI SDKs"
LICENSE = "MIT"
PR = "r8"

inherit task

PACKAGE_ARCH = "${MACHINE_ARCH}"

SECONDARY_BOOTLOADER = ""
SECONDARY_BOOTLOADER_omap3 = "u-boot-spl"
SECONDARY_BOOTLOADER_dm37x-evm = "x-load"
SECONDARY_BOOTLOADER_ti816x = "u-boot-min-sd"
SECONDARY_BOOTLOADER_ti814x = "u-boot-min-sd"
SECONDARY_BOOTLOADER_ti811x = "u-boot-min-sd"

RDEPENDS_${PN} = "\
    dbus \
    dbus-x11 \
    expat \
    glib-2.0 \
    libxml2 \
    libpcre \
    iptables \
    iperf \
    psplash-ti \
    u-boot \
    arago-gpl-notice \
    nfs-utils-client \
    ${SECONDARY_BOOTLOADER} \
    "
