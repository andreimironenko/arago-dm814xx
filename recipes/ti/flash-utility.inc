DESCRIPTION = "Texas Instruments Flash Utility"
LICENSE = "BSD"

PR = "r1"

S = "${WORKDIR}"

installdir = "psp/board-utilties/flash_util/windows"

do_install() {
    install -d ${D}/${installdir}
    install ${S}/setup.exe ${D}/${installdir}
    install ${S}/FlashInstaller.msi ${D}/${installdir}
}

FILES_${PN} += "${installdir}/*" 
