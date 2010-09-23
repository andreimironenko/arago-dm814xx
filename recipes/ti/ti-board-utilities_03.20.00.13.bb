DESCRIPTION = "TI UBL and Serial Boot and Flashing board utilites"
LICENSE = "TI"

SRC_URI = "http://sourceforge.net/projects/dvflashutils/files/OMAP-L138/v2.26/OMAP-L138_FlashAndBootUtils_2_26.tar.gz;name=serialflasher \
http://install.source.dir.com/sd_boot.tar.gz;name=sdboot \
"

SRC_URI[serialflasher.md5sum] = "b07ee8344a56913e449962887c5d49ee"
SRC_URI[serialflasher.sha256sum] = "e6c2bddd0bf4cc16b797fb7417bebffbe1153b968ade85d628d19e718909cdaa"

SRC_URI[sdboot.md5sum] = "8ee28aa26a27b0d172cd99ed66d49b0b"
SRC_URI[sdboot.sha256sum] = "d86f9c819c4d5d4765ab109a33ec872505d804bce05c1e9197b0b9249ae59805"


PR = "r1"

S = "${WORKDIR}/OMAP-L138_FlashAndBootUtils_${PV}"

COMPATIBLE_MACHINE = "(da850-omapl138-evm)"


do_compile () {
	:
}

do_install () {
	install -d ${D}/psp/board-utilities/serialflasher
        cp ${WORKDIR}/OMAP-L138_FlashAndBootUtils_2_26/OMAP-L138/GNU/*.exe ${D}/psp/board-utilities/serialflasher/
	cp -ar ${WORKDIR}/sd_boot/* ${D}/psp/board-utilities/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "psp/board-utilities/*"
PACKAGE_STRIP = "no"
INSANE_SKIP_${PN} = True

