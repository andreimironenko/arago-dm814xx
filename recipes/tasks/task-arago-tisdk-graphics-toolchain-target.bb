DESCRIPTION = "Task to install graphics binaries on sdk target"
PR = "r1"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = "\
    qt4-embedded-dev \
    "

