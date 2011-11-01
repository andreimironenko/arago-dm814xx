# Set the necessary variables to use binary MontaVista Pro 5.0 (/opt/montavista/pro/devkit/arm/v5t_le)
# NOTE: this has not been used/verified in long time and may no longer work
TARGET_VENDOR = "v5tl-montavista"
TARGET_PREFIX = "arm_v5t_le-"
TARGET_CPPFLAGS_append = " -I${SDK_PATH}/target/usr/include "
TARGET_LDFLAGS_prepend = " -L${SDK_PATH}/target/lib -Wl,-rpath-link,${SDK_PATH}/target/lib "
TOOLCHAIN_TYPE = "external"
TOOLCHAIN_BRAND = "generic"
