DESCRIPTION = "Task to install multimedia binaries on ${MACHINE}"
PR = "r38"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

MULTIMEDIA = ""

MULTIMEDIA_DVSDK_COMMON = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    ti-data \
    task-arago-gst \
    gstreamer-ti \
    ti-tisdk-loadmodule \
"

MULTIMEDIA_EZSDK_COMMON = " \
    ti-syslink-examples \
    ti-codec-engine-examples \
    ti-uia \
    ti-media-controller-utils \
    ti-media-controller-loader \
    ti-data \
    ti-omtb \
    ti-omx \
    task-arago-gst \
    ti-omx-core \
    gst-ffmpeg \
    gst-openmax-ti \
    ti-rpe-examples \
"

MULTIMEDIA_dm365 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    ti-dvtb \
    ti-dvsdk-demos \
    ti-dvsdk-demos-qtinterface \
    "

MULTIMEDIA_dm355 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    "

MULTIMEDIA_dm6446 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    "

MULTIMEDIA_omapl137 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    "

MULTIMEDIA_omapl138 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    "

# Use gst-ffmpeg for AM devices
MULTIMEDIA_am180x-evm = " \
    task-arago-gst \
    gst-ffmpeg \
    "

MULTIMEDIA_am181x-evm = " \
    task-arago-gst \
    gst-ffmpeg \
    "

MULTIMEDIA_dm6467 = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    "

MULTIMEDIA_dm37x-evm = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
#    ti-dvtb \
    ti-dvsdk-demos \
    ti-dvsdk-demos-qtinterface \
    "

MULTIMEDIA_omap3evm = " \
    ${MULTIMEDIA_DVSDK_COMMON} \
    ti-dvtb \
    ti-dvsdk-demos \
    ti-dvsdk-demos-qtinterface \
    "

MULTIMEDIA_am37x-evm = " \
    task-arago-gst \
    gst-ffmpeg \
    amsdk-av-files \
    "

MULTIMEDIA_am3517-evm = " \
    task-arago-gst \
    gst-ffmpeg \
    amsdk-av-files \
    "

MULTIMEDIA_beagleboard = " \
    task-arago-gst \
    gst-ffmpeg \
    amsdk-av-files \
    "

MULTIMEDIA_am45x-evm = " \
    task-arago-gst \
    gst-ffmpeg \
    "

MULTIMEDIA_ti816x = " \
    ${MULTIMEDIA_EZSDK_COMMON} \
    ti-firmware \
    "

MULTIMEDIA_ti814x = " \
    ${MULTIMEDIA_EZSDK_COMMON} \
    ti-firmware \
    "

MULTIMEDIA_ti33x = " \
    task-arago-gst \
    gst-ffmpeg \
    amsdk-av-files \
    "

RDEPENDS_${PN} = "\
    ${MULTIMEDIA} \
    "

