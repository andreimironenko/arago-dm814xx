DESCRIPTION = "AM Benchmarks"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_benchmarks/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "46"
PV = "1.0"
PR = "r1+svnr${SRCPV}"

INSANE_SKIP_${PN} = "True"

SRC_URI = "svn://gforge.ti.com/svn/am_benchmarks/;module=trunk;proto=https;user=anonymous;pswd=''"

PLATFORM_armv5te = "arm9"
PLATFORM ?= "cortex-a8"

S = "${WORKDIR}/trunk/${PLATFORM}"

do_compile() {
	# don't build debug version
	touch debug
	export CROSS_COMPILE=${TARGET_PREFIX}
	make release
}

do_install() {
	make DESTDIR=${D} install
}
