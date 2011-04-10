DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti814x"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Centaurus_PSP/TI814X-LINUX-PSP-04.01.00.03.tgz;name=psptarball"
SRC_URI[psptarball.md5sum] = "3f4e5ff4837802a27a0a2e42489c0e57"
SRC_URI[psptarball.sha256sum] = "80b384605cfb48cd8fcaadecc37caec9fe1f13c733ad3c775f07c7ecc18755c2"

S = ${WORKDIR}/TI814X-LINUX-PSP-04.01.00.03/host-tools

PACKAGE_STRIP = "no"

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/host-tools
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/host-tools/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
INSANE_SKIP_${PN} = "True"
