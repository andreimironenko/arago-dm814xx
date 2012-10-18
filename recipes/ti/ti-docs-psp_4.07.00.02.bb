DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

PSPVERSION = "TI81XX-LINUX-PSP-04.07.00.02"

SRC_URI = "http://install.source.dir.local/${PSPVERSION}-docs.tar.gz;name=psptarball"

SRC_URI[psptarball.md5sum] = "b7d2775bcbe559cef54a2f404a89c668"
SRC_URI[psptarball.sha256sum] = "89d4aed7e4b23a45f5f71b2e728aa0272c20231968da77dbd1f3d67e067d7064"

S = ${WORKDIR}/${PSPVERSION}/docs

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/docs
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/docs/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
