DESCRIPTION = "Texas Instruments Flash Utility"
LICENSE = "BSD"

INC_PR = "r3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}"

installdir = "host-tools/flash_utils/windows"

do_install() {
    install -d ${D}/${installdir}
    install ${S}/setup.exe ${D}/${installdir}
    install ${S}/FlashInstaller.msi ${D}/${installdir}
    install ${S}/Release_Notes.txt ${D}/${installdir}
}

FILES_${PN} += "${installdir}/*" 
