DESCRIPTION = "Task to build and install Board Support Package sources (or development header) packages on host"
PR = "r33"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS_COMMON = "\
    ti-tisdk-licenses-src \
    u-boot-src \
    "

BSP_COMPONENTS = ""

BSP_COMPONENTS_dm365 = "\
    ti-tisdk-setup \
    linux-davinci-src \
    ti-tisdk-mksdboot \
    "

BSP_COMPONENTS_dm355 = "\
    ti-linux-driver-examples-src \
    linux-davinci-src \
    "

BSP_COMPONENTS_dm6446 = "\
    ti-linux-driver-examples-src \
    linux-davinci-src \
    "

BSP_COMPONENTS_dm6467 = "\
    ti-linux-driver-examples-src \
    linux-davinci-src \
    "

BSP_COMPONENTS_append_omap3 = "\
    linux-omap3-src \
    x-load-src \
    "

BSP_COMPONENTS_append_ti816x = "\
    linux-omap3-src \
    pinmux-utility \
    "

# Add components to omap3 components
BSP_COMPONENTS_append_omap3evm = "\
    ti-tisdk-setup \
    ti-tisdk-mksdboot \
    flash-utility \
    pinmux-utility \
    "

BSP_COMPONENTS_append_dm37x-evm = "\
    ti-tisdk-setup \
    ti-tisdk-mksdboot \
    flash-utility \
    "

# Add components to omap3 components
BSP_COMPONENTS_append_am37x-evm = "\
    ti-tisdk-setup \
    flash-utility \
    pinmux-utility \
    "

BSP_COMPONENTS_omapl138 = "\
    ti-tisdk-setup \
    ti-tisdk-mksdboot \
    linux-omapl1-src \
    pinmux-utility \
    "

RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS_COMMON} \
    ${BSP_COMPONENTS} \
    "
