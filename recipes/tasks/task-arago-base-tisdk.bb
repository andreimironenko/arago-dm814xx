DESCRIPTION = "Additional packages beyond console packages shared by TI SDKs"
LICENSE = "MIT"
PR = "r5"

inherit task

PACKAGE_ARCH = "${MACHINE_ARCH}"

SECONDARY_BOOTLOADER = ""
SECONDARY_BOOTLOADER_omap3 = "x-load"
SECONDARY_BOOTLOADER_ti816x = "u-boot-min-sd"

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
    ${SECONDARY_BOOTLOADER} \
    "
