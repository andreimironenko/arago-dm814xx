DESCRIPTION = "Task to install multimedia binaries on ${MACHINE}"
PR = "r31"
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
    ti-dvtb \
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
    "

MULTIMEDIA_ti816x = " \
    task-arago-gst \
    gst-ffmpeg \
    "

RDEPENDS_${PN} = "\
    ${MULTIMEDIA} \
    "

