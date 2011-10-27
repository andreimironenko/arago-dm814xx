DESCRIPTION = "Simple Qt web display using webkit"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/matrix_browser"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

PR = "r3"

SRCREV = "8f50865be383e2b3b77704544924939e5a095056"
BRANCH ?= "master"

SRC_URI = "git://gitorious.org/matrix-gui-v2/matrix_browser.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

inherit qt4e

do_install() {
	install -d ${D}/${bindir}
    install -m 0755 ${S}/matrix_browser ${D}/${bindir}
}
