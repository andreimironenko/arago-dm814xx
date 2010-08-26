DESCRIPTION = "Task to install Board Support Package binaries on ${MACHINE}"
PR = "r16"
LICENSE = "MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS = ""

BSP_COMPONENTS_dm355 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_dm365 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_dm6446 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_dm6467 = "\
    ti-linux-driver-examples \
    "

BSP_COMPONENTS_da850-omapl138-evm = "\
    ti-linux-driver-examples-omapl \
    "

BSP_COMPONENTS_omap3 = "\
    x-load \
    "

BSP_COMPONENTS_append_omap3evm = "\
    ti-linux-driver-examples-omap3 \
    "    

BSP_COMPONENTS_append_dm37x-evm = "\
    ti-linux-driver-examples-omap3 \
    "    

RDEPENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    "

RRECOMMENDS_${PN} = "\
    kernel-modules \
    kernel-image \
    kernel-vmlinux \
    u-boot \
    "
