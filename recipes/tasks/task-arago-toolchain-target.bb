DESCRIPTION = "Target packages for a standalone Arago SDK or external toolchain"
ALLOW_EMPTY = "1"
PR = "r10"

PACKAGES = "${PN}"

# Stuff contained in this SDK is largely taken from task-sdk-base.bb.
# This is a starting point, and nothing more at present -- please fill
# this out with a reasonable set of development tools for a Arago image.
# Also feel free to remove stuff that's silly.

RDEPENDS_${PN} = "\
    glibc \
    glibc-dbg \
    virtual-libc-dev \
    glibc-utils \
    libsegfault \
    glibc-thread-db \
    libgcc \
    libstdc++ \
    libstdc++-dev \
    linux-libc-headers-dev \
    gdbserver \
    alsa-dev \
    alsa-lib-dev \
    alsa-utils-dev \
    curl-dev \
    util-linux-ng-dev \
    i2c-tools-dev \
    freetype-dev \
    jpeg-dev \
    lzo-dev \
    libopkg-dev \
    libpng-dev \
    readline-dev \
    libts-dev \
    libusb-compat-dev \
    libusb1-dev \
    libvolume-id-dev \
    zlib-dev \
    ncurses-dev \
    opkg-dev \
#    sysvinit-dev \
    "
