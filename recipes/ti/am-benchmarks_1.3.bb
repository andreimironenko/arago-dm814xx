#NOTE: This package is currently only supported for the Angstrom
#      distribution.  Other distributions and toolchains may or
#      may not work.

DESCRIPTION = "AM Benchmarks"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_benchmarks/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "66"
PR = "r4+svnr${SRCPV}"

SRC_URI = "svn://gforge.ti.com/svn/am_benchmarks/;module=trunk;proto=https;user=anonymous;pswd='' \
           https://gforge.ti.com/gf/download/frsrelease/607/5032/armbenchmarks_1.0.tar.gz;name=benchmarkstarball"

S = "${WORKDIR}/trunk"

MATRIX_FILES_DIR = "${WORKDIR}/armbenchmarks"
require recipes/matrix/matrix-gui-apps.inc

do_configure() {
    # Find all the objects.mk files for the Release target
    files=`find ${BASE_PACKAGE_ARCH} -name "objects.mk" | grep Release`
    for f in $files
    do
        sed -i -e 's|LIBS :=|LIBS := ${LDFLAGS} |' $f
    done
}

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

SRC_URI[benchmarkstarball.md5sum] = "4c0831b45ecbf412c82a61144282372b"
SRC_URI[benchmarkstarball.sha256sum] = "8d9f8f4682e48ab0e40e2379a17e9c636a5812a4648754c30740e552a13e2b7d"
