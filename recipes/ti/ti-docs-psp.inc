DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

DEPENDS = "ti-post-process-wiki-native"

RELNOTESFILE = "${@bb.data.getVar('RELNOTESTOPIC', d, 1).replace('/','_')}"
USERGUIDEFILE = "${@bb.data.getVar('USERGUIDETOPIC', d, 1).replace('/','_')}"
DATASHEETFILE = "${@bb.data.getVar('DATASHEETTOPIC', d, 1).replace('/','_')}"

RELNOTESURL = "http://wiki.davincidsp.com/index.php?title=${RELNOTESTOPIC}"
USERGUIDEURL = "http://wiki.davincidsp.com/index.php?title=${USERGUIDETOPIC}"
DATASHEETURL = "http://wiki.davincidsp.com/index.php?title=${DATASHEETTOPIC}"


do_fetch () {
    mkdir -p ${WORKDIR}/${P}
    cd ${WORKDIR}/${P}

    if [ -n "${RELNOTESTOPIC}" ]; then
        wget --directory-prefix=${WORKDIR}/${P}/${RELNOTESFILE} --html-extension --convert-links --page-requisites --no-host-directories ${RELNOTESURL}
    fi
    if [ -n "${USERGUIDETOPIC}" ]; then
        wget --directory-prefix=${WORKDIR}/${P}/${USERGUIDEFILE} --html-extension --convert-links --page-requisites --no-host-directories ${USERGUIDEURL}
    fi
    if [ -n "${DATASHEETTOPIC}" ]; then
        wget --directory-prefix=${WORKDIR}/${P}/${DATASHEETFILE} --html-extension --convert-links --page-requisites --no-host-directories ${DATASHEETURL}
    fi
}

do_install () {
	install -d ${D}/${installdir}/ti-psp-tree
    if [ -n "${RELNOTESTOPIC}" ]; then
        htmlfiles=`ls ${WORKDIR}/${P}/${RELNOTESFILE}/*.html`
        post-process-tiwiki.pl ${RELNOTESURL} $htmlfiles
        htmldoc --webpage --landscape -f ${D}/${installdir}/ti-psp-tree/${RELNOTESFILE}.pdf $htmlfiles
    fi
    if [ -n "${USERGUIDETOPIC}" ]; then
	htmlfiles=`ls ${WORKDIR}/${P}/${USERGUIDEFILE}/*.html`
        post-process-tiwiki.pl ${USERGUIDEURL} $htmlfiles
	htmldoc --webpage --landscape -f ${D}/${installdir}/ti-psp-tree/${USERGUIDEFILE}.pdf $htmlfiles
    fi
    if [ -n "${DATASHEETTOPIC}" ]; then
	htmlfiles=`ls ${WORKDIR}/${P}/${DATASHEETFILE}/*.html`
	post-process-tiwiki.pl ${DATASHEETURL} $htmlfiles
	htmldoc --webpage --landscape -f ${D}/${installdir}/ti-psp-tree/${DATASHEETFILE}.pdf $htmlfiles
    fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
