DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

PSPVERSION = "TI81XX-LINUX-PSP-04.07.00.01"

SRC_URI = "http://install.source.dir.local/${PSPVERSION}-docs.tar.gz;name=psptarball"

SRC_URI[psptarball.md5sum] = "f5c372a20abb0de56aa180af9c0c6a7f"
SRC_URI[psptarball.sha256sum] = "d8494a7c696f9a83d4548cc137e03cfdbfd77d794186846ba75d108bfb53d9a9"

S = ${WORKDIR}/${PSPVERSION}/docs

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/docs
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/docs/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
