DESCRIPTION = "Scripts to create bootable SD card for ${MACHINE}"
HOMEPAGE_dm365 = "http://processors.wiki.ti.com/index.php/SD_card_boot_and_flashing_tool_for_DM355_and_DM365"
LICENSE = GPLv2

PR = "r25"

require ti-paths.inc
require ti-staging.inc

PLATFORM_omapl138 = "omapl138"
PLATFORM_omap3 = "omap35x"

SRC_URI = "file://mksdboot.sh \
	   file://README \
           file://setup.htm \
           file://top_${PLATFORM}_evm.png \
           file://README.boot.scr \
           file://windows_users.htm \
"

SRCREV_dm365 = "9713"
SRC_URI_dm365 = "http://www.linux-davinci.info/data/boot/dm3xx_sd_boot-6.1.tgz;name=dm365sdboot \
  file://mksdboot.sh "
S_dm365 = "${WORKDIR}"

SRC_URI[dm365sdboot.md5sum] = "3e3360a1c539b48a9229babdfd6a0dbb"
SRC_URI[dm365sdboot.sha256sum] = "9047b6faa751169b96c9ca55bcc173259895fd5fb01c74f3226d19fce4c5eea4"



do_compile () {
	:
}

do_configure_prepend_omap3evm () {
   sed -i -e s:mpurate=1000:mpurate=720:g ${WORKDIR}/mksdboot.sh
   sed -i -e s:TMS320DM3730:OMAP3530:g ${WORKDIR}/mksdboot.sh
   sed -i -e s:mem=128M@0x88000000::g ${WORKDIR}/mksdboot.sh
   sed -i -e s:TMS320DM3730:OMAP3530:g ${WORKDIR}/windows_users.htm
   sed -i -e s:TMS320DM3730:OMAP3530:g ${WORKDIR}/setup.htm
   sed -i -e s:DM37x:OMAP3530:g ${WORKDIR}/windows_users.htm
   sed -i -e s:DM37x:OMAP3530:g ${WORKDIR}/setup.htm
   sed -i -e s:dm3730:omap3530:g ${WORKDIR}/setup.htm
}

do_install () {
	mkdir -p ${D}/${installdir}/bin/
	cp ${WORKDIR}/mksdboot.sh ${D}/${installdir}/bin/
    	chmod +x ${D}/${installdir}/bin/mksdboot.sh
	cp ${WORKDIR}/setup.htm ${D}/${installdir}/bin/
	cp ${WORKDIR}/top_${PLATFORM}_evm.png ${D}/${installdir}/bin/
	cp ${WORKDIR}/windows_users.htm ${D}/${installdir}/bin
	cp ${WORKDIR}/README.boot.scr ${D}/${installdir}/bin
}

do_install_omapl138 () {
        mkdir -p ${D}/${installdir}/bin/
        cp ${WORKDIR}/mksdboot.sh ${D}/${installdir}/bin/
        chmod +x ${D}/${installdir}/bin/mksdboot.sh
        cp ${WORKDIR}/setup.htm ${D}/${installdir}/bin/
        cp ${WORKDIR}/top_${PLATFORM}_evm.png ${D}/${installdir}/bin/
        cp ${WORKDIR}/README.boot.scr ${D}/${installdir}/bin
}


do_install_dm365 () {
	mkdir -p ${D}/${installdir}/bin/
	mkdir -p ${D}/${installdir}/bin/dm3xx_sd_boot-6.1
	mkdir -p ${D}/${installdir}/bin/dm3xx_sd_boot-6.1/bin.x86
	cp ${S}/mksdboot.sh ${D}/${installdir}/bin/
	cp ${S}/dm3xx_sd_boot-6.1/dm3xx_sd_boot ${D}/${installdir}/bin/dm3xx_sd_boot-6.1
	cp ${S}/dm3xx_sd_boot-6.1/dm3xx_sd.config ${D}/${installdir}/bin/dm3xx_sd_boot-6.1
	cp ${S}/dm3xx_sd_boot-6.1/bin.x86/dm3xx_boot_make_image ${D}/${installdir}/bin/dm3xx_sd_boot-6.1/bin.x86/
        mkdir -p ${D}/${installdir}/bin/dm3xx_sd_boot-6.1/sdcard_flash
	cp ${S}/dm3xx_sd_boot-6.1/sdcard_flash/sdcard_flash_DM36x.bin ${D}/${installdir}/bin/dm3xx_sd_boot-6.1/sdcard_flash
	
}

FILES_${PN} = "${installdir}/*"
PACKAGE_ARCH = "${MACHINE_ARCH}"
INSANE_SKIP_${PN} = True

