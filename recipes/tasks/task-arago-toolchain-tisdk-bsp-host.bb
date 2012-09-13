DESCRIPTION = "Task to build and install Board Support Package sources (or development header) packages on host"
PR = "r48"
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
    ti-tisdk-relnotes \
    ti-docs-sdg \
    ti-docs-quickstart \
    ti-docs-psp \
    ti-board-utilities \
    ti-linux-driver-examples-src \
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
    x-load-src \
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
    ti-tisdk-relnotes \
    ti-docs-sdg \
    ti-docs-qsg-hardcopy \
    ti-docs-hug-hardcopy \
    ti-docs-psp \
    ti-linux-driver-examples-omap3-src \
    pinmux-utility \
    "

BSP_COMPONENTS_append_dm37x-evm = "\
    linux-omap3-src \
    ti-tisdk-setup \
    ti-tisdk-mksdboot \
    flash-utility \
    ti-tisdk-relnotes \
    ti-docs-sdg \
    ti-docs-qsg-hardcopy \
    ti-docs-hug-hardcopy \
    ti-docs-psp \
    ti-linux-driver-examples-omap3-src \
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
    linux-src \
    ti-tisdk-setup \
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
    linux-omapl1-src \
    ti-tisdk-relnotes \
    ti-docs-sdg \
    ti-docs-qsg-hardcopy \
    ti-docs-psp \
    ti-board-utilities \
    ti-linux-driver-examples-omapl-src \
    "

BSP_COMPONENTS_ti816x = "\
    ti-tisdk-setup \
    ti-tisdk-relnotes \
    ti-docs-qsg-hardcopy \
    ti-docs-sdg \
    ti-docs-psp \
    ti-host-tools-psp \
    ti-psp-driver-examples \
    ti-tisdk-mksdboot \
    linux-omap3-src \
    pinmux-utility \
    ti-docs-linux-devkit \
    ti-docs-dsp-devkit \
    "

BSP_COMPONENTS_ti814x = "\
    ti-tisdk-setup \
    ti-tisdk-relnotes \
    ti-docs-qsg-hardcopy \
    ti-docs-sdg \
    ti-docs-psp \
    ti-host-tools-psp \
    ti-psp-driver-examples \
    ti-tisdk-mksdboot \
    linux-omap3-src \
    pinmux-utility \
    ti-docs-linux-devkit \
    ti-docs-dsp-devkit \
    "

BSP_COMPONENTS_ti811x = "\
    ti-tisdk-setup \
    ti-tisdk-relnotes \
    ti-docs-sdg \
    ti-docs-psp \
    ti-host-tools-psp \
    ti-psp-driver-examples \
    ti-tisdk-mksdboot \
    linux-omap3-src \
    ti-docs-linux-devkit \
    ti-docs-dsp-devkit \
    j5eco-tvp5158-src \
    ahudemo-src \
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
