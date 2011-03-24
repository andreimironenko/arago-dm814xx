DESCRIPTION = "Texas Instruments Pinmux Utility"
LICENSE = "BSD"

INC_PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}"

require ti-paths.inc

do_install() {
    install -d ${D}/${installdir}/ti-psp-tree/board-utilities/pinmux_utils/windows
    install ${S}/Pin_Mux_Utility.msi ${D}/${installdir}/ti-psp-tree/board-utilities/pinmux_utils/windows
    install ${S}/setup.exe ${D}/${installdir}/ti-psp-tree/board-utilities/pinmux_utils/windows
    install ${S}/Release_Notes.txt ${D}/${installdir}/ti-psp-tree/board-utilities/pinmux_utils/windows
}

FILES_${PN} += "${installdir}/*"
