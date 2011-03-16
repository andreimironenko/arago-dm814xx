DESCRIPTION = "Scripts to create bootable SD card for ${MACHINE}"
LICENSE = GPLv2

PR = "r29"

require ti-paths.inc
require ti-staging.inc

PLATFORM_omapl138 = "omapl138"
PLATFORM_omap3 = "omap35x"
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
    install -d ${D}/${installdir}/bin/
    install -m 0755 ${WORKDIR}/mksdboot.sh ${D}/${installdir}/bin/
    install -m 0644 ${WORKDIR}/setup.htm ${D}/${installdir}/bin/
    install -m 0644 ${WORKDIR}/top_${PLATFORM}_evm.png ${D}/${installdir}/bin/
    install -m 0644 ${WORKDIR}/README.boot.scr ${D}/${installdir}/bin
	install -m 0644 ${WORKDIR}/windows_users.htm ${D}/${installdir}/bin
}

FILES_${PN} = "${installdir}/*"
PACKAGE_ARCH = "${MACHINE_ARCH}"
INSANE_SKIP_${PN} = True

