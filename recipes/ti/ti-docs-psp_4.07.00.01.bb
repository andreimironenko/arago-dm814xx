DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

PSPVERSION = "TI81XX-LINUX-PSP-04.07.00.01"

SRC_URI = "http://install.source.dir.local/${PSPVERSION}-docs.tar.gz;name=psptarball"

SRC_URI[psptarball.md5sum] = "eb45a14e54ca633ce147b3077f6ee1c3"
SRC_URI[psptarball.sha256sum] = "a5e3c1f891ecb5230652643a138b641d70a7e92ece2aed00713b4609556ba5a8"

S = ${WORKDIR}/${PSPVERSION}/docs

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/docs
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/docs/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
