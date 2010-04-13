DESCRIPTION = "PSP Release Notes"
LICENSE = "CC-BY-SA"
PR = "r0"

PV = "03.02.00.31"

require ti-paths.inc

DEPENDS = "ti-post-process-wiki-native"

COMPATIBLE_MACHINE = "dm6467-evm"

FILENAME = "DaVinci_PSP_03.02_Beta_\(r31\)_Release_Notes"
HTTPSRC = "http://wiki.davincidsp.com/index.php?title=${FILENAME}"

do_fetch () {
	mkdir -p ${WORKDIR}/${P}
	cd ${WORKDIR}/${P}
        wget --directory-prefix=${WORKDIR}/${P}/${FILENAME} --html-extension --convert-links --page-requisites --no-host-directories ${HTTPSRC}
}

do_install () {
	install -d ${D}/${installdir}/ti-psp-tree
	htmlfiles=`ls ${WORKDIR}/${P}/${FILENAME}/*.html`
	post-process-tiwiki.pl $htmlfiles
	htmldoc --webpage --landscape -f ${D}/${installdir}/ti-psp-tree/${FILENAME}.pdf $htmlfiles
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
