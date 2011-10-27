DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti814x"

PSPVERSION = "TI814X-LINUX-PSP-04.01.00.06"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Centaurus_PSP/${PSPVERSION}.tgz;name=psptarball"

SRC_URI[psptarball.md5sum] = "e51b3452f99344cdab31e1b7daea8477"
SRC_URI[psptarball.sha256sum] = "45f729c28b7b0e230e766e359f87ef9de0290d7b13e37bdc4db66dc8e1115761"

S = ${WORKDIR}/${PSPVERSION}/host-tools

PACKAGE_STRIP = "no"

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/host-tools
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/host-tools/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
INSANE_SKIP_${PN} = "True"
