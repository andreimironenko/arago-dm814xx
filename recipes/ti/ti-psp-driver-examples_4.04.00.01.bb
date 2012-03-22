DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti814x|ti816x"

PSPVERSION = "TI81XX-LINUX-PSP-04.04.00.01"

SRC_URI = "ftp://ftp.india.ti.com//PSP/Releases/ODC/TI81XX_PSP/${PSPVERSION}.tgz;name=psptarball"

SRC_URI[psptarball.md5sum] = "9531716c5796dfc7a03185bb19dbeea1"
SRC_URI[psptarball.sha256sum] = "e1b7c1f7ca977b2af3fd0eeb04657437e94a13d8f7ec7ff747ec73bfc1fcedfc"

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
