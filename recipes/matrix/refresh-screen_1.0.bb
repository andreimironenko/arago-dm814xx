DESCRIPTION = "Simple Application to force a screen refresh"
HOMEPAGE = "https://gitorious.org/matrix-gui-v2/refresh-screen"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

PR = "r0"

SRCREV = "02540639be560734914f88fc2627b996d47510f0"
BRANCH ?= "master"

SRC_URI = "git://gitorious.org/matrix-gui-v2/refresh-screen.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

inherit qt4e

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/refresh_screen ${D}/${bindir}
}
