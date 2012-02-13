DESCRIPTION = "Linux Bluetooth Stack HCI Debugger Tool."
SECTION = "console"
PRIORITY = "optional"
DEPENDS = "bluez-libs"
LICENSE = "GPLv2"
PR = "r0"

SRC_URI = "http://bluez.sourceforge.net/download/bluez-hcidump-${PV}.tar.gz"
S = "${WORKDIR}/bluez-hcidump-${PV}"

EXTRA_OECONF = "--with-bluez-libs=${STAGING_LIBDIR} --with-bluez-includes=${STAGING_INCDIR}"

inherit autotools

SRC_URI[md5sum] = "b160f0672276398344eebe9df1b37a2c"
SRC_URI[sha256sum] = "a6cc20b95b6b1a28ff336aad91e124555231628689225c1155e8cd7aac1af86d"
