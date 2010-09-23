LICENSE = GPLv2

PR = "r17"
installdir="/"

PLATFORM_da850-omapl138-evm = "omapl138"
PLATFORM_omap3 = "omap35x"

SRC_URI = "file://mksdboot.sh \
	   file://README \
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

FILES_${PN} = "${installdir}/*"
PACKAGE_ARCH = "${MACHINE_ARCH}"

