DESCRIPTION = "Task install multimedia binaries on ${MACHINE}"
PR = "r1"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

MULTIMEDIA_SOURCETREE_dm365 = " \
    ti-dmai-apps \
    ti-codec-engine-examples \
    "

RRECOMMENDS_${PN} = "\
    ${MULTIMEDIA_SOURCETREE} \
    "
