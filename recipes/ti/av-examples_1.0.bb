DESCRIPTION = "Linux Audio Video Example Applications"
HOMEPAGE = "https://gforge.ti.com/gf/project/av_examples/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "13"
PR = "r6"

DEPENDS += "virtual/kernel"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(am37x-evm|am3517-evm)"

SRC_URI = "svn://gforge.ti.com/svn/av_examples/;module=trunk;proto=https;user=anonymous;pswd='' \
           https://gforge.ti.com/gf/download/frsrelease/734/5223/displayexamples_1.2.tar.gz"

# TODO: Export LDFLAGS from Rules.make to eliminate use of INSANE_SKIP which would otherwise lead to discrepancies between host and arago binaries
INSANE_SKIP_${PN} = "True"

S = "${WORKDIR}/trunk"

MATRIX_FILES_DIR = "${WORKDIR}/displayexamples"
require recipes/matrix/matrix-gui-apps.inc

do_compile() {
	touch debug
	export CROSS_COMPILE=${TARGET_PREFIX}
	make release LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" PLATFORM="${MACHINE}"
}

do_install() {
	make DESTDIR=${D} install
}

# Make sure the display submenu has been installed
RDEPENDS += matrix-gui-submenus-display

# Add the matrix directory to the list of FILES
FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "dcb98e8b0acdc5f94b6e90bea0a8f911"
SRC_URI[sha256sum] = "6b0ca83b5a1afeac970ca5277fa4206b8bf5590858edb5cdac7f0a67c905305a"
