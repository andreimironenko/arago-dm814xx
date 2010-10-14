DESCRIPTION = "TI UBL and Serial Boot and Flashing board utilites"
LICENSE = "TI"

SRC_URI = "http://sourceforge.net/projects/dvflashutils/files/OMAP-L138/v2.26/OMAP-L138_FlashAndBootUtils_2_26.tar.gz;name=serialflasher \
http://install.source.dir.com/DaVinci-PSP-SDK-03.20.00.13.tgz;name=psprelease \
"

SRC_URI[serialflasher.md5sum] = "b07ee8344a56913e449962887c5d49ee"
SRC_URI[serialflasher.sha256sum] = "e6c2bddd0bf4cc16b797fb7417bebffbe1153b968ade85d628d19e718909cdaa"

SRC_URI[psprelease.md5sum] = "440fbaf8ed5e6857f2b9027012f4aed5"
SRC_URI[psprelease.sha256sum] = "2fbbfa42ce426572edff1f14cbf8542d901c2daca5c94fed8f534521f35a5e08"

PR = "r2"

S = "${WORKDIR}/OMAP-L138_FlashAndBootUtils_${PV}"

COMPATIBLE_MACHINE = "(da850-omapl138-evm)"


do_compile () {
	# compile uflash tool (the prebuilt binary was for 64-bit arch !!!)
	tar zxf ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.13/src/utils/mmcsd-writer-03.20.00.13.tar.gz -C ${WORKDIR}
	cd ${WORKDIR}/mmcsd-writer-03.20.00.13/
	gcc -o uflash uflash.c
}

do_install () {
	install -d ${D}/psp/board-utilities/serialflasher
	install -d ${D}/psp/board-utilities/src/boot-strap
	install -d ${D}/psp/board-utilities/src/utils
	install -d ${D}/psp/board-utilities/images/boot-strap
	install -d ${D}/psp/board-utilities/images/utils
        cp ${WORKDIR}/OMAP-L138_FlashAndBootUtils_2_26/OMAP-L138/GNU/*.exe ${D}/psp/board-utilities/serialflasher/
	cp ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.13/src/boot-strap/* ${D}/psp/board-utilities/src/boot-strap
	cp ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.13/images/boot-strap/omapl1x8/* ${D}/psp/board-utilities/images/boot-strap
	cp ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.13/src/utils/* ${D}/psp/board-utilities/src/utils/
	cp ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.13/images/utils/omapl1x8/* ${D}/psp/board-utilities/images/utils/
	cp ${WORKDIR}/mmcsd-writer-03.20.00.13/uflash ${D}/psp/board-utilities/images/utils/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "psp/board-utilities/*"
PACKAGE_STRIP = "no"
INSANE_SKIP_${PN} = True

