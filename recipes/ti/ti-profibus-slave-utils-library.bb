DESCRIPTION = "Profibus Utility library for use by Profibus Slave Application"
HOMEPAGE = "https://gforge.ti.com/gf/project/pru_sw/"
LICENSE = "BSD"
PRIORITY = "optional"
PR = "r1+svnr${SRCPV}"

RDEPENDS += "ti-profibus-slave-utils-driver"
DEPENDS += "virtual/kernel"
PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "am181x-evm"

SRCREV = "20"
SRC_URI = "svn://gforge.ti.com/svn/pru_sw/;module=trunk;proto=https;user=anonymous;pswd=''"

S = "${WORKDIR}/trunk/peripheral_lib/profibus_hwutils/interface"

do_compile () {
	oe_runmake KERNEL_SRC_DIR="${STAGING_KERNEL_DIR}"
}

do_install () {
	install -d ${D}/${libdir}
	install -m 0755 ${S}/../lib/libprofibus_hwutils.a ${D}/${libdir}/
}
