DESCRIPTION = "Task to install graphics binaries on sdk target"
PR = "r2"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

LIBGLES = ""
LIBGLES_omap3 = "libgles-omap3-dev"

RDEPENDS_${PN} = "\
    qt4-embedded-dev \
    ${LIBGLES} \
    "

