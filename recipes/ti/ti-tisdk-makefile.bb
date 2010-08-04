DESCRIPTION = "Package contain Makefile and Rule.make used for building DVSDK components"
LICENSE = "TI"

require ti-paths.inc

SRC_URI = "\
	file://Makefile \
  	file://Rules.make \
"
PR = "r40"

# Update makefile to use silicon rev 5.x for DM3730 EVM.
do_configure_prepend_dm3730-am3715-evm () { 
         sed -i -e 's:OMAPES=3.x:OMAPES=5.x:g' ${WORKDIR}/Makefile
         sed -i -e 's:gfx_rel_es3.x:gfx_rel_es5.x:g' ${WORKDIR}/Makefile
}

do_install () {
	install -d ${D}/${installdir}
	install  ${WORKDIR}/Makefile ${D}/${installdir}/Makefile
	install  ${WORKDIR}/Rules.make ${D}/${installdir}/Rules.make
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
