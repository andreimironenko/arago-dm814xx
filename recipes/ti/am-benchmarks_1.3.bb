#NOTE: This package is currently only supported for the Angstrom
#      distribution.  Other distributions and toolchains may or
#      may not work.

DESCRIPTION = "AM Benchmarks"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_benchmarks/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "65"
PR = "r2+svnr${SRCPV}"

INSANE_SKIP_${PN} = "True"

SRC_URI = "svn://gforge.ti.com/svn/am_benchmarks/;module=trunk;proto=https;user=anonymous;pswd=''"

S = "${WORKDIR}/trunk"

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
