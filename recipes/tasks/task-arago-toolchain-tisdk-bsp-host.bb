DESCRIPTION = "Task to build and install Board Support Package sources (or development header) packages on host"
PR = "r45"
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

BSP_COMPONENTS_append_ti816x = "\
    linux-omap3-src \
    pinmux-utility \
    "

BSP_COMPONENTS_append_ti814x = "\
    linux-omap3-src \
    "

BSP_COMPONENTS_append_ti33x = "\
    linux-am335x-src \
    ti-tisdk-setup \
    pinmux-utility \
    bootcamp-lects \
    "

BSP_COMPONENTS_append_omap4 = "\
    linux-omap4-src \
    "

# Add components to omap3 components
BSP_COMPONENTS_append_omap3evm = "\
    linux-omap3-src \
    ti-tisdk-setup \
    ti-tisdk-mksdboot \
    flash-utility \
    pinmux-utility \
    "

BSP_COMPONENTS_append_dm37x-evm = "\
    linux-omap3-src \
    ti-tisdk-setup \
    ti-tisdk-mksdboot \
    flash-utility \
    x-load-src \
    "

# Add components to omap3 components
BSP_COMPONENTS_append_am37x-evm = "\
    linux-omap3-src \
    ti-tisdk-setup \
    flash-utility \
    pinmux-utility \
    av-examples-src \
    bootcamp-lects \
    "

BSP_COMPONENTS_append_beagleboard = "\
    linux-omap-src \
    ti-tisdk-setup \
    flash-utility \
    pinmux-utility \
    bootcamp-lects \
    "

BSP_COMPONENTS_append_am3517-evm = "\
    linux-omap3-src \
    ti-tisdk-setup \
    flash-utility \
    pinmux-utility \
    av-examples-src \
    bootcamp-lects \
    "

BSP_COMPONENTS_append_am389x-evm = "\
    ti-tisdk-setup \
    flash-utility \
    pinmux-utility \
    bootcamp-lects \
    "

BSP_COMPONENTS_omapl138 = "\
    ti-tisdk-setup \
    ti-tisdk-mksdboot \
    linux-davinci-src \
    pinmux-utility \
    "

BSP_COMPONENTS_append_am180x-evm = "\
    bootcamp-lects \
    "

BSP_COMPONENTS_append_am181x-evm = "\
    bootcamp-lects \
    "

RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS_COMMON} \
    ${BSP_COMPONENTS} \
    "
