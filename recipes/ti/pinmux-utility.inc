DESCRIPTION = "Texas Instruments Pinmux Utility"
LICENSE = "BSD"

INC_PR = "r0"

S = "${WORKDIR}"

installdir = "psp/board-utilities/pinmux_utils/windows"

do_install() {
    install -d ${D}/${installdir}
    install ${S}/Pin_Mux_Utility.msi ${D}/${installdir}
    install ${S}/setup.exe ${D}/${installdir}
    install ${S}/Release_Notes.txt ${D}/${installdir}
}

FILES_${PN} += "${installdir}/*"
