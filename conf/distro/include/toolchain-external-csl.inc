# Set the necessary variables to use binary CodeSourcery Lite
TOOLCHAIN_VENDOR = "-none"

# External CSL toolchain/glibc
TOOLCHAIN_TARGET_EXCLUDE += "\
    libc6 \
    libc6-dev \
    glibc-extra-nss \
    libgcc1 \
    linux-libc-headers-dev \
    libthread-db1 \
    sln \
    libstdc++ \
    libstdc++-dev \
    "

PREFERRED_VERSION_gdbserver = "6.6"
PREFERRED_VERSION_gdb = "6.6"
PREFERRED_VERSION_gdb-cross-sdk = "6.6"
PREFERRED_PROVIDER_gdbserver = "gdbserver"
