DESCRIPTION = "Package contain Makefile and Rule.make used for building DVSDK components"
LICENSE = "TI"

PR = "r1"

SRC_URI = "\
    file://ti-tisdk-licenses/APACHEv2.txt \
    file://ti-tisdk-licenses/ECLIPSEv1.txt \
    file://ti-tisdk-licenses/GPLv2.txt \
    file://ti-tisdk-licenses/GPLv3.txt \
    file://ti-tisdk-licenses/LGPLv2.1.txt \
    file://ti-tisdk-licenses/LGPLv3.txt \
    file://ti-tisdk-licenses/MPLv1.1.txt \
    file://ti-tisdk-licenses/TI_TSPA.txt \
    file://ti-tisdk-licenses/BSD.txt \
    file://ti-tisdk-licenses/MIT.txt \
    file://ti-tisdk-licenses/creativecommons.txt \
    file://ti-tisdk-licenses/openssl.txt \
"
PR = "r0"

do_install () {
	install -d ${D}/${installdir}/ti-docs-tree
	cp -pPrf ${WORKDIR}/licenses/* ${D}/${installdir}/ti-docs-tree
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
