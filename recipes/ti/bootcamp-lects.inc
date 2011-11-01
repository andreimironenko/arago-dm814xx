DESCRIPTION = "Texas Instruments Bootcamp Lectures"
LICENSE = "BSD"

INC_PR = "r2"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}"

installdir = "docs/"

do_install() {
    install -d ${D}/${installdir}
    cp -rfp ${S}/training ${D}/${installdir}

}

FILES_${PN} += "${installdir}/*"
