DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "(c6a816x-evm|dm816x-evm|dm816x-custom)"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Netra_GIT_PSP/TI816X-LINUX-PSP-04.00.00.09.tgz;name=psptarball"
SRC_URI[psptarball.md5sum] = "e9d048c655b2e433046a1446d6ae6fcb"
SRC_URI[psptarball.sha256sum] = "8af0a82a09db5d175d0d957952186538fe65fa9461dca249bf1a9ca04b474019"

S = ${WORKDIR}/TI816X-LINUX-PSP-04.00.00.09/host-tools

PACKAGE_STRIP = "no"

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/host-tools
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/host-tools/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
INSANE_SKIP_${PN} = "True"
