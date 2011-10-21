#NOTE: This package is currently only supported for the Angstrom
#      distribution.  Other distributions and toolchains may or
#      may not work.

DESCRIPTION = "AM Benchmarks"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_benchmarks/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "66"
PR = "r5+svnr${SRCPV}"

SRC_URI = "svn://gforge.ti.com/svn/am_benchmarks/;module=trunk;proto=https;user=anonymous;pswd='' \
           https://gforge.ti.com/gf/download/frsrelease/671/5094/armbenchmarks_1.1.tar.gz;name=benchmarkstarball"

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

SRC_URI[benchmarkstarball.md5sum] = "8ac27584c294e10b4648f73c4d333ffe"
SRC_URI[benchmarkstarball.sha256sum] = "724c0b620c14652eb6ef8891ee5e2a2d90606ccebf8b39234e689b7df739b386"
