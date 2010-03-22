DESCRIPTION = "Task to build and install Board Support package sources (or development header) packages on host"
PR = "r7"
LICENSE="MIT"
ALLOW_EMPTY = "1"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

BSP_COMPONENTS_dm365 = "\
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    "

BSP_COMPONENTS_dm355 = "\
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    "

BSP_COMPONENTS_dm6446 = "\
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    "

BSP_COMPONENTS_dm6467 = "\
    ti-linux-driver-examples-sourcetree \
    linux-davinci-staging-sourcetree \
    "

BSP_COMPONENTS_da830-omapl137-evm = "\
    linux-omapl1-sourcetree \
    "

BSP_COMPONENTS_da850-omapl138-evm = "\
    linux-omapl1-sourcetree \
    "

BSP_COMPONENTS_omap3 = "\
    linux-omap3-sourcetree \
    "

RRECOMMENDS_${PN} = "\
    ${BSP_COMPONENTS} \
    "

