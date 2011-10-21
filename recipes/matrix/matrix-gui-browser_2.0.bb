DESCRIPTION = "Simple Qt web display using webkit"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/matrix_browser"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

PR = "r2"

SRCREV = "274185aa2fc7e086ed18abe64fc4a55a696e33dc"
BRANCH ?= "qwebview"

SRC_URI = "git://gitorious.org/matrix-gui-v2/matrix_browser.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

inherit qt4e

do_install() {
	install -d ${D}/${bindir}
    install -m 0755 ${S}/matrix_browser ${D}/${bindir}
}
