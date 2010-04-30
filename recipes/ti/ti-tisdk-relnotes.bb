DESCRIPTION = "DVSDK 4.00 Release Notes"
LICENSE = "CC-BY-SA"

PR="r0"

require ti-paths.inc

SRC_URI = "http://install.source.dir.local/DVSDK_4.00_Release_Notes.pdf;name=relnotes"
SRC_URI[relnotes.md5sum] = "7dc2d7bf7a39f8fdd4ac404a127b2d37"
SRC_URI[relnotes.sha256sum] = "b4d47992d7d96087716ccb75dc53e37f86af933b35295a7e675dbb07b168d30f"

do_install () {
    mkdir -p ${D}/${installdir}
    cp ${WORKDIR}/DVSDK_4.00_Release_Notes.pdf ${D}/${installdir}/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
