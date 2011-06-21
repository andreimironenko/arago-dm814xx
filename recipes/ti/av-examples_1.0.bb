DESCRIPTION = "Linux Audio Video Example Applications"
HOMEPAGE = "https://gforge.ti.com/gf/project/av_examples/"
LICENSE = "BSD"
SECTION = "system"
PRIORITY = "optional"

SRCREV = "10"
PR = "r0"

DEPENDS += "virtual/kernel"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(am37x-evm|am3517-evm)"

SRC_URI = "svn://gforge.ti.com/svn/av_examples/;module=trunk;proto=https;user=anonymous;pswd=''"

# TODO: Export LDFLAGS from Rules.make to eliminate use of INSANE_SKIP which would otherwise lead to discrepancies between host and arago binaries
INSANE_SKIP_${PN} = "True"

S = "${WORKDIR}/trunk"

do_compile() {
	touch debug
	export CROSS_COMPILE=${TARGET_PREFIX}
	make release LINUXKERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" PLATFORM="${MACHINE}"
}

do_install() {
	make DESTDIR=${D} install
}
