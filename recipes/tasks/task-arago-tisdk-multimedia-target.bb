DESCRIPTION = "Task to install multimedia binaries on ${MACHINE}"
PR = "r3"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

MULTIMEDIA_dm365 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    "

RDEPENDS_${PN} = "\
    ${MULTIMEDIA} \
    "

