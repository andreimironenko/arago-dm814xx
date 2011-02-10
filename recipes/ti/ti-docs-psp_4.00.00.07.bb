DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "(c6a816x-evm|dm816x-evm)"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Netra_GIT_PSP/TI816X-LINUX-PSP-04.00.00.07.tgz;name=psptarball"
SRC_URI[psptarball.md5sum] = "2ff5df892fa07581c8e54455695908c7"
SRC_URI[psptarball.sha256sum] = "d3abc7b333100864938b3d600a5a2734bf8235b439caf0246c89d2f33f1666c3"

S = ${WORKDIR}/TI816X-LINUX-PSP-04.00.00.07/docs

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/docs
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/docs/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
