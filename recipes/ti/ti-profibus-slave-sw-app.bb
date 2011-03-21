DESCRIPTION = "Loads Profibus Slave Evaluation Application"
HOMEPAGE = "https://gforge01.dal.design.ti.com/gf/project/pru_sw"
LICENSE = "BSD"
PRIORITY = "optional"
PR = "r1"

DEPENDS += "ti-profibus-slave-utils-library ti-pru-sw-edma-library"
RDEPENDS += "ti-pru-sw-edma-driver ti-profibus-slave-utils-driver"
PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "am181x-evm"

SRCREV = "6"
SRC_URI = "svn://gforge01.dal.design.ti.com/svn/pru_sw/;module=trunk;proto=https;user=anonymous;pswd=''"

S = "${WORKDIR}/trunk/am181x_profibus_dpslave_app/src"

do_install () {
        install -d ${D}/${docdir}/slave/
        install -d ${D}/${libdir}
        install -d ${D}/${bindir}
        install -m 0755 ${S}/../doc/* ${D}/${docdir}/slave/
        install -m 0755 ${S}/../bin/* ${D}/${bindir}/
        install -m 0755 ${S}/../lib/libprofibus_dpslave.so ${D}/${libdir}
}

FILES_${PN} += "${libdir}/libprofibus_dpslave.so"
FILES_${PN} += "${docdir}/slave/*"

INSANE_SKIP_${PN} = "True"
INSANE_SKIP_${PN}-dev = "True"
