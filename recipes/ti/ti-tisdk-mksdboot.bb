LICENSE = GPLv2

require ti-paths.inc
require ti-staging.inc

PR = "r11"

SRC_URI = "file://mksdboot.sh \
	   file://README \
"

SRC_URI_append_omap3 = "file://setup.htm \
 file://top_omap35x_evm.png \
 file://windows_users.htm"

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
}

do_install () {
	mkdir -p ${D}/${installdir}/bin/
	cp ${WORKDIR}/mksdboot.sh ${D}/${installdir}/bin/
    	chmod +x ${D}/${installdir}/bin/mksdboot.sh
}

do_install_append_omap3 () {
	cp ${WORKDIR}/setup.htm ${D}/${installdir}/bin/
	cp ${WORKDIR}/top_omap35x_evm.png ${D}/${installdir}/bin/
	cp ${WORKDIR}/windows_users.htm ${D}/${installdir}/bin
}

FILES_${PN} = "${installdir}/*"

