DESCRIPTION = "Scripts to create bootable SD card for ${MACHINE}"
LICENSE = GPLv2

PR = "r29"

require ti-paths.inc

PLATFORM_omapl138 = "omapl138"
PLATFORM_omap3 = "omap35x"
PLATFORM_ti816x = "ti816x"
PLATFORM_ti814x = "ti814x"
PLATFORM_am180x-evm = "am180x"
PLATFORM_am181x-evm = "am1810"
PLATFORM_am3517-evm = "am3517"
PLATFORM_dm365-evm = "dm365"
PLATFORM_dm368-evm = "dm368"

SRC_URI = "file://mksdboot.sh \
           file://setup.htm \
           file://top_${PLATFORM}_evm.png \
           file://README.boot.scr \
           file://windows_users.htm \
"

SRC_URI_append_omapl138 = "file://c6748.htm "

SRCREV_dm365 = "9843"
SRC_URI_dm365 = "svn://winsvn.sanb.design.ti.com/SDOApps/apps/sdk_productization/trunk/sdk_productization/boot;module=dm365;proto=http;user=anonymous;pswd='' "
S_dm365 = "${WORKDIR}/dm365"

do_compile () {
	:
}

do_configure_prepend_omap3evm () {
   sed -i -e s:mpurate=1000:mpurate=720:g ${WORKDIR}/mksdboot.sh
   sed -i -e s:TMS320DM3730:OMAP3530:g ${WORKDIR}/mksdboot.sh
   sed -i -e s:mem=55M@0x80000000:mem=99M:g ${WORKDIR}/mksdboot.sh
   sed -i -e s:mem=128M@0x88000000::g ${WORKDIR}/mksdboot.sh
   sed -i -e s:TMS320DM3730:OMAP3530:g ${WORKDIR}/windows_users.htm
   sed -i -e s:TMS320DM3730:OMAP3530:g ${WORKDIR}/setup.htm
   sed -i -e s:DM37x:OMAP3530:g ${WORKDIR}/windows_users.htm
   sed -i -e s:DM37x:OMAP3530:g ${WORKDIR}/setup.htm
   sed -i -e s:dm3730:omap3530:g ${WORKDIR}/setup.htm
}

do_install () {
    install -d ${D}/bin
    install -m 0755 ${WORKDIR}/mksdboot.sh ${D}/bin
    install -m 0755 ${WORKDIR}/setup.htm ${D}/bin
    install -m 0755 ${WORKDIR}/top_${PLATFORM}_evm.png ${D}/bin
    install -m 0755 ${WORKDIR}/windows_users.htm ${D}/bin
    install -m 0755 ${WORKDIR}/README.boot.scr ${D}/bin
}

do_install_da850-omapl138-evm () {
        mkdir -p ${D}/bin/
        cp ${WORKDIR}/mksdboot.sh ${D}/bin/
        chmod +x ${D}/bin/mksdboot.sh
        cp ${WORKDIR}/setup.htm ${D}/bin/
        cp ${WORKDIR}/c6748.htm ${D}/bin/
        cp ${WORKDIR}/top_${PLATFORM}_evm.png ${D}/bin/
        cp ${WORKDIR}/README.boot.scr ${D}/bin
}


do_install_dm365 () {
	mkdir -p ${D}/bin/
	mkdir -p ${D}/bin/dm3xx_sd_boot-6.1
	mkdir -p ${D}/bin/dm3xx_sd_boot-6.1/bin.x86
	cp ${S}/mksdboot.sh ${D}/bin/
	cp ${S}/dm3xx_sd_boot-6.1/dm3xx_sd_boot ${D}/bin/dm3xx_sd_boot-6.1
	cp ${S}/dm3xx_sd_boot-6.1/dm3xx_sd.config ${D}/bin/dm3xx_sd_boot-6.1
	cp ${S}/dm3xx_sd_boot-6.1/bin.x86/dm3xx_boot_make_image ${D}/bin/dm3xx_sd_boot-6.1/bin.x86/
        mkdir -p ${D}/bin/dm3xx_sd_boot-6.1/sdcard_flash
	cp ${S}/dm3xx_sd_boot-6.1/sdcard_flash/sdcard_flash_DM36x.bin ${D}/bin/dm3xx_sd_boot-6.1/sdcard_flash
	
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
INSANE_SKIP_${PN} = True

