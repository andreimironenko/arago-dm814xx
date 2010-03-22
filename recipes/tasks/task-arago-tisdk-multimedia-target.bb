DESCRIPTION = "Task to install multimedia binaries on ${MACHINE}"
PR = "r7"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

MULTIMEDIA_dm365 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    "

MULTIMEDIA_dm355 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    "

MULTIMEDIA_dm6446 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    "

MULTIMEDIA_da830-omapl137-evm = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    "

MULTIMEDIA_da850-omapl138-evm = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    "

MULTIMEDIA_dm6467-evm = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    "

MULTIMEDIA_omap3 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    "

RDEPENDS_${PN} = "\
    ${MULTIMEDIA} \
    "

