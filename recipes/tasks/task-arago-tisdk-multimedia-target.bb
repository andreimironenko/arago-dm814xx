DESCRIPTION = "Task to install multimedia binaries on ${MACHINE}"
PR = "r18"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

MULTIMEDIA_dm365 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    ti-dvsdk-demos \
    ti-data \
    ti-dvtb \
    task-arago-gst \
    gstreamer-ti \
    "

MULTIMEDIA_dm355 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    ti-dvsdk-demos \
    ti-data \
    "

MULTIMEDIA_dm6446 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    task-arago-gst \
    gstreamer-ti \
    "

MULTIMEDIA_da830-omapl137-evm = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    task-arago-gst \
    gstreamer-ti \
    "

MULTIMEDIA_da850-omapl138-evm = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    task-arago-gst \
    gstreamer-ti \
	showoff \
    "

MULTIMEDIA_dm6467-evm = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    ti-dvsdk-demos \
    ti-data \
    task-arago-gst \
    gstreamer-ti \
    "

MULTIMEDIA_omap3 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    task-arago-gst \
    gstreamer-ti \
    "

RDEPENDS_${PN} = "\
    ${MULTIMEDIA} \
    "

