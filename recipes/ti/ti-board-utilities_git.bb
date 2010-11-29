
COMPATIBLE_MACHINE = "(dm365)"
require ti-paths.inc

PV = "git"
PR = "r5"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "http://install.source.dir.local/ti-board-utilities_git.tar.gz;name=boardutilitiestarball"

SRC_URI[boardutilitiestarball.md5sum] = "cb0f6f86a55bdeec8fa8d6508c614926"
SRC_URI[boardutilitiestarball.sha256sum] = "2327427c7f9f2873f0e9d168d336a2289dc3a769a4d4109a596caea9a5e1e4d2"

S = "${WORKDIR}/flash-utils/DM36x"

do_compile () {
	:
}

do_install () {
	install -d ${D}/psp/board-utilities/ubl
    cp -ar ${S}/* ${D}/psp/board-utilities/ubl/
    find ${D}/psp/board-utilities/ubl -name .svn -type d | xargs rm -rf
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "psp/board-utilities/*"
PACKAGE_STRIP = "no"
INSANE_SKIP_${PN} = True

