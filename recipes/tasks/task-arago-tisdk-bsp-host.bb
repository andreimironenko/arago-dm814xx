DESCRIPTION = "Task to build and install Board Support package sources (or development header) packages on host"
PR = "r10"
LICENSE="MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS_dm365 = "\
    ti-docs-psprelnotes \
    ti-board-utilities \
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    u-boot-sourcetree \
    "

BSP_COMPONENTS_dm355 = "\
    ti-docs-psprelnotes \
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
    ti-docs-psprelnotes \
    ti-board-utilities \
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    u-boot-sourcetree \
    "

BSP_COMPONENTS_da830-omapl137-evm = "\
    ti-docs-psprelnotes \
    linux-omapl1-sourcetree \
    u-boot-sourcetree \
    "

BSP_COMPONENTS_da850-omapl138-evm = "\
    ti-docs-psprelnotes \
    linux-omapl1-sourcetree \
    u-boot-sourcetree \
    "

BSP_COMPONENTS_omap3 = "\
    linux-omap3-sourcetree \
    u-boot-sourcetree \
    "

RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    "

