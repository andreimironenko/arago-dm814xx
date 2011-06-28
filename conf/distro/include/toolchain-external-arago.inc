# Set the necessary variables to use binary Arago toolchain
TOOLCHAIN_VENDOR = "-arago"

TOOLCHAIN_INCLUDE_IN_SDK = "1"

PREFERRED_PROVIDER_binutils-cross-sdk = "external-toolchain-sdk-${TOOLCHAIN_BRAND}"
PREFERRED_PROVIDER_gcc-cross-sdk = "external-toolchain-sdk-${TOOLCHAIN_BRAND}"
PREFERRED_PROVIDER_gdb-cross-sdk = "external-toolchain-sdk-${TOOLCHAIN_BRAND}"
