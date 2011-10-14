DESCRIPTION = "Texas Instruments Bootcamp Lectures"
LICENSE = "BSD"

INC_PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}"

installdir = "docs/training"

do_install() {
    install -d ${D}/${installdir}
    cp -rfp ${S}/training ${D}/${installdir}

}

FILES_${PN} += "${installdir}/*"
