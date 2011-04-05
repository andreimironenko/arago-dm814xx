DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "(c6a814x-evm|dm814x-evm)"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Centaurus_PSP/TI814X-LINUX-PSP-04.01.00.03.tgz;name=psptarball"
SRC_URI[psptarball.md5sum] = "3f4e5ff4837802a27a0a2e42489c0e57"
SRC_URI[psptarball.sha256sum] = "80b384605cfb48cd8fcaadecc37caec9fe1f13c733ad3c775f07c7ecc18755c2"

S = ${WORKDIR}/TI814X-LINUX-PSP-04.01.00.03/src/examples

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
