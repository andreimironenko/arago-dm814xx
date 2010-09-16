DESCRIPTION = "Additional packages beyond console packages shared by TI SDKs"
LICENSE = "MIT"
PR = "r5"

inherit task

PACKAGE_ARCH = "${MACHINE_ARCH}"

PRIMARY_BOOTLOADER = ""
PRIMARY_BOOTLOADER_omap3 = "u-boot-omap3"
PRIMARY_BOOTLOADER_dm365 = "u-boot-davinci"
PRIMARY_BOOTLOADER_omapl138 = "u-boot-omapl1"


SECONDARY_BOOTLOADER = ""
SECONDARY_BOOTLOADER_omap3 = "x-load"

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
    ${PRIMARY_BOOTLOADER} \
    ${SECONDARY_BOOTLOADER} \
    "
