DESCRIPTION = "Package contain Makefile and Rule.make used for building DVSDK components"
LICENSE = "TI"

require ti-paths.inc

SRC_URI = "\
	file://licenses/* \
"
PR = "r0"

do_install () {
	install -d ${D}/${installdir}/ti-docs-tree
	cp -pPrf ${WORKDIR}/licenses/* ${D}/${installdir}/ti-docs-tree
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
