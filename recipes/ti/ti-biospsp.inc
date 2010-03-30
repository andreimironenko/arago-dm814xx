DESCRIPTION = "TI BIOS Platform Support Package (PSP)"
HOMEPAGE = " "

SECTION = "devel"
LICENSE = "TI BSD"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "(da830-omapl137-evm|da850-omapl138-evm)"

PR = "r1"

S = "${WORKDIR}/pspdrivers_${PV}"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Freon_BIOS_PSP/REL.BIOSPSP.01.30.01.GA/BIOSPSP_${PV}.tar.gz;name=biospsptarball"

DEPENDS = "ti-dspbios ti-edma3lld ti-cgt6x" 

do_configure() {
}

do_compile() {
}


do_install() {
    install -d ${D}${BIOSPSP_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${BIOSPSP_INSTALL_DIR_RECIPE}
}

