DESCRIPTION = "Linux Audio Video Example Applications"
HOMEPAGE = "https://gforge.ti.com/gf/project/av_examples/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "12"
PR = "r4"

DEPENDS += "virtual/kernel"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(am37x-evm|am3517-evm)"

SRC_URI = "svn://gforge.ti.com/svn/av_examples/;module=trunk;proto=https;user=anonymous;pswd='' \
           https://gforge.ti.com/gf/download/frsrelease/670/5093/displayexamples_1.1.tar.gz"

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

SRC_URI[md5sum] = "8547bb43e87b65aea41e74bbd7fb4018"
SRC_URI[sha256sum] = "8b73673ded6f34596a3bc928b9f6fa06bbd16b4f4d682d7dac8b85cba945635f"
