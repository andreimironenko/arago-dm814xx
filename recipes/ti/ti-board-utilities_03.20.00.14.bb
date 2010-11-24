DESCRIPTION = "TI UBL and Serial Boot and Flashing board utilites"
LICENSE = "TI"

SRC_URI = "http://sourceforge.net/projects/dvflashutils/files/OMAP-L138/v2.30/OMAP-L138_FlashAndBootUtils_2_30.tar.gz;name=serialflasher \
http://software-dl.ti.com/dsps/dsps_public_sw/psp/LinuxPSP/DaVinci_03_20/03_20_00_14//exports/DaVinci-PSP-SDK-03.20.00.14.tgz;name=psprelease \
"

SRC_URI[serialflasher.md5sum] = "56a26449784334106562fb4cd9a950a5"
SRC_URI[serialflasher.sha256sum] = "9a33b608925eafddfd051dc0c74e6e584ca896b499e116e70d17eebf81e789cf"

SRC_URI[psprelease.md5sum] = "c3f801be6ddcbb9ba3af2e5592c05d98"
SRC_URI[psprelease.sha256sum] = "c45a91ae12fe8bdc27647c651ec14dc7874921f0189528a411045fe301787ef6"

PR = "r2"

S = "${WORKDIR}/OMAP-L138_FlashAndBootUtils_${PV}"

COMPATIBLE_MACHINE = "(da850-omapl138-evm)"


do_compile () {
	# compile uflash tool (the prebuilt binary was for 64-bit arch !!!)
	tar zxf ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.14/src/utils/mmcsd-writer-03.20.00.14.tar.gz -C ${WORKDIR}
	cd ${WORKDIR}/mmcsd-writer-03.20.00.14/
	gcc -o uflash uflash.c
}

do_install () {
	install -d ${D}/psp/board-utilities/serialflasher
	install -d ${D}/psp/board-utilities/src/boot-strap
	install -d ${D}/psp/board-utilities/src/utils
	install -d ${D}/psp/board-utilities/images/boot-strap
	install -d ${D}/psp/board-utilities/images/utils
        cp ${WORKDIR}/OMAP-L138_FlashAndBootUtils_2_30/OMAP-L138/GNU/*.exe ${D}/psp/board-utilities/serialflasher/
	cp ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.14/src/boot-strap/* ${D}/psp/board-utilities/src/boot-strap
	cp ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.14/images/boot-strap/omapl1x8/* ${D}/psp/board-utilities/images/boot-strap
	cp ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.14/src/utils/* ${D}/psp/board-utilities/src/utils/
	cp ${WORKDIR}/DaVinci-PSP-SDK-03.20.00.14/images/utils/omapl1x8/* ${D}/psp/board-utilities/images/utils/
	cp ${WORKDIR}/mmcsd-writer-03.20.00.14/uflash ${D}/psp/board-utilities/images/utils/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "psp/board-utilities/*"
PACKAGE_STRIP = "no"
INSANE_SKIP_${PN} = True

