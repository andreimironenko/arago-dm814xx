DESCRIPTION = "Texas Instruments Flash Utility"
LICENSE = "BSD"

INC_PR = "r2"

S = "${WORKDIR}"

installdir = "host-tools/flash_utils/windows"

do_install() {
    install -d ${D}/${installdir}
    install ${S}/setup.exe ${D}/${installdir}
    install ${S}/FlashInstaller.msi ${D}/${installdir}
}

FILES_${PN} += "${installdir}/*" 
