DESCRIPTION = "Task to install graphics binaries on ${MACHINE}"
PR = "r1"
LICENSE="MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

LIBGLES = ""

LIBGLES_omap3 = " \
    libgles-omap3-sourcetree \
"
    
RDEPENDS_${PN} = "\
    ${LIBGLES} \
    "
