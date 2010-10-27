
COMPATIBLE_MACHINE = "(dm365|dm368)"

PV = "git"
PR = "r1"

SRC_URI = "http://install.source.dir.local/ti-board-utilities_git.tar.gz;name=boardutilitiestarball"

SRC_URI[boardutilitiestarball.md5sum] = "cb0f6f86a55bdeec8fa8d6508c614926"
SRC_URI[boardutilitiestarball.sha256sum] = "2327427c7f9f2873f0e9d168d336a2289dc3a769a4d4109a596caea9a5e1e4d2"

S = "${WORKDIR}/flash-utils/DM36x"

do_compile () {
	:
}

do_install () {
	:
}

