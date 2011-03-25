DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "(c6a816x-evm|dm816x-evm)"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Netra_GIT_PSP/TI816X-LINUX-PSP-04.00.00.10.tgz;name=psptarball"
SRC_URI[psptarball.md5sum] = "4dd9a7a5ad4a1f377edbbbc404c1b601"
SRC_URI[psptarball.sha256sum] = "6320289273cb3ef9ec43910e055273b78973d867bbb1b0793517f224e0c7752c"

S = ${WORKDIR}/TI816X-LINUX-PSP-04.00.00.10/host-tools

PACKAGE_STRIP = "no"

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/host-tools
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/host-tools/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
INSANE_SKIP_${PN} = "True"
