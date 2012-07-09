DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

PSPVERSION = "TI81XX-LINUX-PSP-04.07.00.01"

SRC_URI = "http://install.source.dir.local/${PSPVERSION}-docs.tar.gz;name=psptarball"

SRC_URI[psptarball.md5sum] = "cbbc2b372b1e6eaa0fd5580730b2fbf4"
SRC_URI[psptarball.sha256sum] = "519e0e599245887a57258487d1eb00585032e4afad4ed31f7ebb8e384a372f75"

S = ${WORKDIR}/${PSPVERSION}/docs

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/docs
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/docs/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
