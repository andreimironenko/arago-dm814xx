DESCRIPTION = "Task to install multimedia binaries on ${MACHINE}"
PR = "r4"
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

RDEPENDS_${PN} = "\
    ${MULTIMEDIA} \
    "

