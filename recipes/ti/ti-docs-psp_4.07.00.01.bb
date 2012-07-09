DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

PSPVERSION = "TI81XX-LINUX-PSP-04.07.00.01"

SRC_URI = "http://install.source.dir.local/${PSPVERSION}-docs.tar.gz;name=psptarball"

SRC_URI[psptarball.md5sum] = "b0ffbaa1ddb07fc21ac13f105cd4841e"
SRC_URI[psptarball.sha256sum] = "1ad6c384d4dcd1be21318839a87b4f65b09127f6c846f5f152d7f3f3d9690b79"

S = ${WORKDIR}/${PSPVERSION}/docs

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/docs
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/docs/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
