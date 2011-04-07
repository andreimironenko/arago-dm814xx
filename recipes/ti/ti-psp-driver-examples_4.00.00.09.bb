DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "(c6a816x-evm|dm816x-evm|dm816x-custom)"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Netra_GIT_PSP/TI816X-LINUX-PSP-04.00.00.09.tgz;name=psptarball"
SRC_URI[psptarball.md5sum] = "e9d048c655b2e433046a1446d6ae6fcb"
SRC_URI[psptarball.sha256sum] = "8af0a82a09db5d175d0d957952186538fe65fa9461dca249bf1a9ca04b474019"

S = ${WORKDIR}/TI816X-LINUX-PSP-04.00.00.09/src/examples

do_prepsources () {
    cd ${S} && tar -zxf examples.tar.gz
}

addtask prepsources after do_unpack before do_configure

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/examples
    cp -pPrf ${S}/examples/* ${D}${installdir}/ti-psp-tree/examples/
    cp -pPrf ${S}/pcie ${D}${installdir}/ti-psp-tree/examples/
    cp -pPrf ${S}/watchdog ${D}${installdir}/ti-psp-tree/examples/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
