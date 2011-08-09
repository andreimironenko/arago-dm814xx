DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti814x"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Centaurus_PSP/TI814X-LINUX-PSP-04.01.00.05.tgz;name=psptarball"

SRC_URI[psptarball.md5sum] = "bc5d9e666821d42dcffb1e727d90fe34"
SRC_URI[psptarball.sha256sum] = "af8c47eda2b6c21ac7ea1bdf118d6ce6739b2a991e2a2cdce10d4d64335e5d7f"

S = ${WORKDIR}/TI816X-LINUX-PSP-04.01.00.05/src/examples

PACKAGE_STRIP = "no"

do_prepsources () {
    cd ${S} && tar -zxf examples.tar.gz
}

addtask prepsources after do_unpack before do_configure

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/examples
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/examples/

    install -d ${D}/${installdir}/ti-psp-tree/prebuilt-images/examples
    cp -pPrf ${WORKDIR}/TI814X-LINUX-PSP-04.01.00.05/images/examples/* ${D}/${installdir}/ti-psp-tree/prebuilt-images/examples
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
INSANE_SKIP_${PN} = "True"
