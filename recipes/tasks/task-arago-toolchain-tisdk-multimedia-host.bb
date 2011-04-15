DESCRIPTION = "Task to build and install multimedia source (or development header) packages on host"
PR = "r21"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

MULTIMEDIA_SOURCETREE = ""

MULTIMEDIA_DVSDK_COMMON = " \
    ti-framework-components-src \
    ti-codec-engine-src \
    ti-linuxutils-src \
    ti-xdctools-src \
    ti-dmai-src \
    ti-xdais-src \
    gstreamer-ti-src \
 "

MULTIMEDIA_EZSDK_COMMON = " \
    ti-sysbios-src \
    ti-xdctools-src \
    ti-edma3lld-src \
    ti-framework-components-src \
    ti-linuxutils-src \
    ti-xdais-src \
    ti-osal-src \
    ti-ipc-src \
    ti-syslink-src \
    ti-syslink-dev \
    ti-codec-engine-src \
    ti-omtb-src \
    ti-uia-src \
 "

MULTIMEDIA_SOURCETREE_am37x-evm = ""

MULTIMEDIA_SOURCETREE_omap3 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    ti-codecs-omap3530-src \
    ti-local-power-manager-src \
    ti-dvsdk-demos-src\
    "

MULTIMEDIA_SOURCETREE_append_omap3evm = " \
    ti-dvtb-src \
"

MULTIMEDIA_SOURCETREE_dm355 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    ti-codecs-dm355-src \
    "

MULTIMEDIA_SOURCETREE_dm365 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    ti-codecs-dm365-src \
    ti-dm365mm-module-src \
    ti-dvtb-src \
    ti-dvsdk-demos-src\
    "

MULTIMEDIA_SOURCETREE_dm6446 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    ti-codecs-dm6446-src \
    ti-local-power-manager-src \
    "

MULTIMEDIA_SOURCETREE_dm6467 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    ti-codecs-dm6467-src \
    "

MULTIMEDIA_SOURCETREE_omapl137 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    ti-codecs-omapl137-src \
    "

MULTIMEDIA_SOURCETREE_omapl138 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    ti-codecs-omapl138-src \
    "

MULTIMEDIA_SOURCETREE_dm816x-evm = " \
    ${MULTIMEDIA_EZSDK_COMMON} \
    ti-omx-libs-sourcetree \
"

MULTIMEDIA_SOURCETREE_dm816x-custom = " \
    ${MULTIMEDIA_EZSDK_COMMON} \
    ti-cgt470-src \
    ti-codecs-dm816x-src \
    ti-ivahd-hdvicp20api-src \
    ti-hdvpss-src \
    ti-omx-src \
    ortp-src \
    "

MULTIMEDIA_SOURCETREE_dm814x-evm = " \
    ${MULTIMEDIA_EZSDK_COMMON} \
    ti-omx-libs-sourcetree \
"
MULTIMEDIA_SOURCETREE_dm814x-custom = " \
    ${MULTIMEDIA_EZSDK_COMMON} \
    ti-cgt470-src \
    ti-codecs-dm814x-src \
    ti-ivahd-hdvicp20api-src \
    ti-hdvpss-src \
    ti-omx-src \
    ortp-src \
    "

# Do not pull DSP sources into AMSDK builds
MULTIMEDIA_SOURCETREE_am180x-evm = ""
MULTIMEDIA_SOURCETREE_am181x-evm = ""

RRECOMMENDS_${PN} = "\
    ${MULTIMEDIA_SOURCETREE} \
    "

