DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti814x|ti816x|ti811x"

PSPVERSION = "TI811X-LINUX-PSP-04.07.00.02"

SRC_URI = "ftp://ftp.india.ti.com//PSP/Releases/ODC/TI81XX_PSP/${PSPVERSION}.tgz;name=psptarball"

SRC_URI[psptarball.md5sum] = "da64fd67a134e509ca320dabfc6f88fe"
SRC_URI[psptarball.sha256sum] = "eff6753e751472966666e8b1d8c07f17f888b2c2fe539364cd8c346243d245be"

S = ${WORKDIR}/${PSPVERSION}/src/examples

PACKAGE_STRIP = "no"

do_prepsources () {
    cd ${S} && tar -zxf examples.tar.gz
}

addtask prepsources after do_unpack before do_configure

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/examples
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/examples/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
INSANE_SKIP_${PN} = "True"
