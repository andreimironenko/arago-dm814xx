DESCRIPTION = "Texas Instruments Pinmux Utility"
LICENSE = "BSD"

PR = "r0"

S = "${WORKDIR}"

require ti-paths.inc

do_install() {
    install -d ${D}/${installdir}/ti-psp-tree/board-utilities/pinmux_utils/windows
    install ${S}/Pin_Mux_Utility.msi ${D}/${installdir}/ti-psp-tree/board-utilities/pinmux_utils/windows
    install ${S}/setup.exe ${D}/${installdir}/ti-psp-tree/board-utilities/pinmux_utils/windows
    install ${S}/Release_Notes.txt ${D}/${installdir}/ti-psp-tree/board-utilities/pinmux_utils/windows
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
