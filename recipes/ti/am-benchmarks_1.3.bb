#NOTE: This package is currently only supported for the Angstrom
#      distribution.  Other distributions and toolchains may or
#      may not work.

DESCRIPTION = "AM Benchmarks"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_benchmarks/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "69"
PR = "r6+svnr${SRCPV}"

SRC_URI = "svn://gforge.ti.com/svn/am_benchmarks/;module=trunk;proto=https;user=anonymous;pswd='' \
           https://gforge.ti.com/gf/download/frsrelease/691/5118/armbenchmarks_1.2.tar.gz;name=benchmarkstarball"

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

SRC_URI[benchmarkstarball.md5sum] = "ba9c6ea496a9687d00fa515c1a7ae046"
SRC_URI[benchmarkstarball.sha256sum] = "c0d1081996be045286a25bd1f770f8e990c37122d26264798015e81478464b72"
