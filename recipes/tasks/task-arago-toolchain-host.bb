DESCRIPTION = "Host packages for a standalone Arago SDK or external toolchain"
PR = "r5"
ALLOW_EMPTY = "1"

inherit sdk

PACKAGES = "${PN}"

RDEPENDS_${PN} = "\
    pkgconfig-sdk \
    opkg-sdk \
    libtool-sdk \
    ${@base_conditional('TOOLCHAIN_INCLUDE_IN_SDK', '1', 'binutils-cross-sdk gcc-cross-sdk gdb-cross-sdk', '', d)} \
    "
