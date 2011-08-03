DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti816x"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Netra_GIT_PSP/TI816X-LINUX-PSP-04.00.00.12.tgz;name=psptarball"

SRC_URI[psptarball.md5sum] = "097f4f8c25095bfee3e421916e1527ba"
SRC_URI[psptarball.sha256sum] = "8e75d4e5bfdd595e1cadba766080dc5a3d19d48528f1249cc91d47708e4615aa"

S = ${WORKDIR}/TI816X-LINUX-PSP-04.00.00.12/src/examples

PACKAGE_STRIP = "no"

do_prepsources () {
    cd ${S} && tar -zxf examples.tar.gz
}

addtask prepsources after do_unpack before do_configure

do_install () {
    install -d ${D}/${installdir}/ti-psp-tree/examples
    cp -pPrf ${S}/* ${D}${installdir}/ti-psp-tree/examples/

    install -d ${D}/${installdir}/ti-psp-tree/prebuilt-images/examples
    cp -pPrf ${WORKDIR}/TI816X-LINUX-PSP-04.00.00.12/images/examples/* ${D}/${installdir}/ti-psp-tree/prebuilt-images/examples
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
INSANE_SKIP_${PN} = "True"
