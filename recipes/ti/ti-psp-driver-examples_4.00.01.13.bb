DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti816x"

PSPVERSION = "TI816X-LINUX-PSP-04.00.01.13"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Netra_GIT_PSP/${PSPVERSION}.tgz;name=psptarball \
"

SRC_URI[psptarball.md5sum] = "1182a036983b39d9ddbfabd52141b88e"
SRC_URI[psptarball.sha256sum] = "8fcbfdd8507664c53d485c11fefdadf04c76ec361869273dd952dc475b455f50"

S = ${WORKDIR}/${PSPVERSION}/src/examples

PACKAGE_STRIP = "no"

do_prepsources () {
    cd ${S} && tar -zxf examples.tar.gz
}

addtask prepsources after do_unpack before do_configure

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/examples
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/examples/

    install -d ${D}/${installdir}/ti-psp-tree/prebuilt-images/examples
    cp -pPrf ${WORKDIR}/${PSPVERSION}/images/examples/* ${D}/${installdir}/ti-psp-tree/prebuilt-images/examples
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
INSANE_SKIP_${PN} = "True"
