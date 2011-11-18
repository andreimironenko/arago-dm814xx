#NOTE: This package is currently only supported for the Angstrom
#      distribution.  Other distributions and toolchains may or
#      may not work.

DESCRIPTION = "AM Benchmarks"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_benchmarks/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "75"
PR = "r8+svnr${SRCPV}"

SRC_URI = "svn://gforge.ti.com/svn/am_benchmarks/;module=trunk;proto=https;user=anonymous;pswd='' \
           https://gforge.ti.com/gf/download/frsrelease/731/5220/armbenchmarks_1.4.tar.gz;name=benchmarkstarball"

S = "${WORKDIR}/trunk"

MATRIX_FILES_DIR = "${WORKDIR}/armbenchmarks"
require recipes/matrix/matrix-gui-apps.inc

do_compile() {
	# don't build debug version
	touch debug
	export CROSS_COMPILE=${TARGET_PREFIX}
	export ARCH=${BASE_PACKAGE_ARCH}
	make release 
}

do_install() {
	export ARCH=${BASE_PACKAGE_ARCH}
	make DESTDIR=${D} install
}

# Make sure the arm submenu has been installed
RDEPENDS += matrix-gui-submenus-arm

# Add the matrix directory to the list of FILES
FILES_${PN} += "${MATRIX_BASE_DIR}/*"

SRC_URI[benchmarkstarball.md5sum] = "ec44d760bd292b7965bb83cf09753995"
SRC_URI[benchmarkstarball.sha256sum] = "cd90753273f154fe4ee002b4fab9a7c26bb167f71676c486da36cce15954b14c"
