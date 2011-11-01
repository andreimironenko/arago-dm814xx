# Settings common to internal toolchains

# Override gcc-4.3 defaults, when building gcc-4.5 toolchain
ANGSTROM_GCC_VERSION			= "4.5"
ANGSTROM_BINUTILS_VERSION		= "2.20.1"
ANGSTROM_BINUTILS_VERSION_armv7a	= "2.20.1"
PREFERRED_VERSION_eglibc		= "2.12"
PREFERRED_VERSION_eglibc-initial	= "2.12"

# Other common settings
TARGET_VENDOR = "-arago"

TOOLCHAIN_INCLUDE_IN_SDK = "1"

PREFERRED_PROVIDER_binutils-cross-sdk = "binutils-cross-sdk"
PREFERRED_PROVIDER_gcc-cross-sdk = "gcc-cross-sdk"
PREFERRED_PROVIDER_gdb-cross-sdk = "gdb-cross-sdk"
PREFERRED_PROVIDER_gdbserver = "gdbserver"
