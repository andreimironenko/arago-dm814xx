DESCRIPTION = "Task to generate source tree packages for TI MULTIMEDIA"
PR = "r2"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

MULTIMEDIA_SOURCETREE_omap3 = " \
    ti-framework-components-sourcetree \
    ti-codec-engine-sourcetree \
    ti-codecs-omap3530-sourcetree \
    ti-linuxutils-sourcetree \
    ti-xdctools-sourcetree \
    ti-dmai-sourcetree \
    ti-xdais-sourcetree \
    ti-local-power-manager-sourcetree \
    "
MULTIMEDIA_SOURCETREE_dm355 = " \
    ti-framework-components-sourcetree \
    ti-codec-engine-sourcetree \
    ti-codecs-dm355-sourcetree \
    ti-linuxutils-sourcetree \
    ti-xdctools-sourcetree \
    ti-dmai-sourcetree \
    ti-xdais-sourcetree \
    "

MULTIMEDIA_SOURCETREE_dm365 = " \
    ti-framework-components-sourcetree \
    ti-codec-engine-sourcetree \
    ti-codecs-dm365-sourcetree \
    ti-linuxutils-sourcetree \
    ti-xdctools-sourcetree \
    ti-dmai-sourcetree \
    ti-xdais-sourcetree \
    "

MULTIMEDIA_SOURCETREE_dm6446 = " \
    ti-framework-components-sourcetree \
    ti-codec-engine-sourcetree \
    ti-codecs-dm6446-sourcetree \
    ti-linuxutils-sourcetree \
    ti-xdctools-sourcetree \
    ti-dmai-sourcetree \
    ti-xdais-sourcetree \
    "

MULTIMEDIA_SOURCETREE_dm6467 = " \
    ti-framework-components-sourcetree \
    ti-codec-engine-sourcetree \
    ti-codecs-dm6467-sourcetree \
    ti-linuxutils-sourcetree \
    ti-xdctools-sourcetree \
    ti-dmai-sourcetree \
    ti-xdais-sourcetree \
    "

RRECOMMENDS_${PN} = "\
    ${MULTIMEDIA_SOURCETREE} \
    "
