DESCRIPTION = "Simple Qt web display using webkit"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/matrix_browser"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

PR = "r4"

SRCREV = "5857a24ef0eaa0835f9e9c5a5f80ce681a1e801e"
BRANCH ?= "master"

SRC_URI = "git://gitorious.org/matrix-gui-v2/matrix_browser.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

inherit qt4e

do_install() {
	install -d ${D}/${bindir}
    install -m 0755 ${S}/matrix_browser ${D}/${bindir}
}
