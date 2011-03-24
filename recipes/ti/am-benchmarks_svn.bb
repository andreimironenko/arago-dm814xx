# NOTE:  This version of the recipe is used only in Arago because the
#        version of the CodeSourcery toolchain (2009q1) currently
#        used by Arago does not yield ideal performance, whereas the
#        version used to build the Dhrystone binary (2008q1) does.
#        This issue does not affect the OE mainline which does not
#        use the CodeSourcery toolchain.
DESCRIPTION = "AM Benchmarks"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_benchmarks/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "56"
PV = "1.1"
PR = "r2+svnr${SRCPV}"

ARCHITECTURE_dm365 = "arm9"
ARCHITECTURE_da850-omapl138-evm = "arm9"
ARCHITECTURE_omap3 = "cortex-a8"
ARCHITECTURE_ti816x = "cortex-a8"
ARCHITECTURE_ti814x = "cortex-a8"
ARCHITECTURE ?= "<UNDEFINED>"

INSANE_SKIP_${PN} = "True"

SRC_URI = "svn://gforge.ti.com/svn/am_benchmarks/;module=trunk;proto=https;user=anonymous;pswd='' "
SRC_URI_append = "https://gforge.ti.com/gf/download/frsrelease/406/3998/dhrystone-static.tar.gz"

SRC_URI[md5sum] = "0b39552d41ba469adfcc92acd7b763d1"
SRC_URI[sha256sum] = "5d57687eeffd1ea9ce0a098904fa25adc4cef3f842910ed4ba5fb2f8806e536c"

S = "${WORKDIR}/trunk/${ARCHITECTURE}"

do_compile() {
	# don't build debug version
	touch debug
	export CROSS_COMPILE=${TARGET_PREFIX}
	make release
}

do_install() {
	make DESTDIR=${D} install
    install -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/${ARCHITECTURE}/dhrystone_${ARCHITECTURE}_static ${D}/${bindir}/dhrystone_static
}
