LICENSE = GPLv2

require ti-paths.inc
require ti-staging.inc

PR = "r4"

SRC_URI = "file://mksdboot.sh \
	   file://README \
"

do_compile () {
	:
}

do_configure_prepend_omap3evm () {
   sed -i -e s:mpurate=1000:mpurate=720:g ${WORKDIR}/mksdboot.sh
}

do_install () {
	mkdir -p ${D}/${installdir}/bin/
	cp ${WORKDIR}/mksdboot.sh ${D}/${installdir}/bin/
    	chmod +x ${D}/${installdir}/bin/mksdboot.sh
}

FILES_${PN} = "${installdir}/bin/*"

