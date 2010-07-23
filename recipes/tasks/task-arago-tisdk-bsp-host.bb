DESCRIPTION = "Task to build and install Board Support package sources (or development header) packages on host"
PR = "r22"
LICENSE="MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS_dm365 = "\
    ti-tisdk-setup \
    ti-tisdk-relnotes \
    ti-docs-sdg \
    ti-docs-quickstart \
    ti-docs-psp \
    ti-board-utilities \
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    u-boot-sourcetree \
    "

BSP_COMPONENTS_dm355 = "\
    ti-docs-psp \
    ti-board-utilities \
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    u-boot-sourcetree \
    "

BSP_COMPONENTS_dm6446 = "\
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    u-boot-sourcetree \
    "

BSP_COMPONENTS_dm6467 = "\
    ti-docs-psp \
    ti-board-utilities \
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    u-boot-sourcetree \
    "

BSP_COMPONENTS_da830-omapl137-evm = "\
    ti-docs-psp \
    linux-omapl1-sourcetree \
    u-boot-sourcetree \
    "

BSP_COMPONENTS_da850-omapl138-evm = "\
    ti-tisdk-setup \
    ti-tisdk-relnotes \
    ti-docs-sdg \
    ti-docs-quickstart \
    ti-docs-psp \
    linux-omapl1-sourcetree \
    u-boot-sourcetree \
    ti-board-utilities \
    ti-linux-driver-examples-omapl-sourcetree \
    "

BSP_COMPONENTS_omap3 = "\
    linux-omap3-sourcetree \
    u-boot-sourcetree \
    ti-linux-driver-examples-omap3-sourcetree \
    "

BSP_COMPONENTS_dm3730-am3715-evm = "\
    ti-tisdk-relnotes \
    ti-docs-sdg \
    linux-omap3-sourcetree \
    u-boot-sourcetree \
    "

RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    "

