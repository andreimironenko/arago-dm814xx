DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "(c6a814x-evm|dm814x-evm)"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Centaurus_PSP/TI814X-LINUX-PSP-04.01.00.02.tgz;name=psptarball"
SRC_URI[psptarball.md5sum] = "01f827831d21841ceb0ddd2bca2835dc"
SRC_URI[psptarball.sha256sum] = "8a2dd8738efaf083718c6ab0da6815d272e23fdf60a6e09117e319386e14c672"

S = ${WORKDIR}/TI814X-LINUX-PSP-04.01.00.02/src/examples

do_prepsources () {
    cd ${S} && tar -zxf examples.tar.gz
}

addtask prepsources after do_unpack before do_configure

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/examples
    cp -pPrf ${S}/examples/* ${D}${installdir}/ti-psp-tree/examples/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"