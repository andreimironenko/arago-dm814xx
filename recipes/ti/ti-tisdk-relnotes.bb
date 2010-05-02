DESCRIPTION = "DVSDK 4.00 Release Notes"
LICENSE = "CC-BY-SA"

PR="r0"

require ti-paths.inc

SRC_URI = "http://install.source.dir.local/DVSDK_4.00_Release_Notes.pdf;name=relnotes"
SRC_URI[relnotes.md5sum] = "c97c9dd98006a2c7114c45e1b35fb8d9"
SRC_URI[relnotes.sha256sum] = "d4a66276818540154320adfe341b058c6a40b46723fce4cc1d652c4faad7b054"

do_install () {
    mkdir -p ${D}/${installdir}
    cp ${WORKDIR}/DVSDK_4.00_Release_Notes.pdf ${D}/${installdir}/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
