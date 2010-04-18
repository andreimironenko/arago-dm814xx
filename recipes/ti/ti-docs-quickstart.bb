DESCRIPTION = "PSP Documentation"
LICENSE = "CC-BY-SA"

require ti-paths.inc

DEPENDS = "ti-post-process-wiki-native"

PR = "r1"

COMPATIBLE_MACHINE = "dm365-evm"

TOPIC = "TMS320DM365_EVM_Quick_Start_Guide"
TOPICFILE = "${@bb.data.getVar('TOPIC', d, 1).replace('/','_')}"
TOPICURL = "http://ap-fpdsp-swapps.dal.design.ti.com/index.php/${TOPIC}"

do_fetch () {
    mkdir -p ${WORKDIR}/${P}
    cd ${WORKDIR}/${P}

    wget --directory-prefix=${WORKDIR}/${P}/${TOPICFILE} --html-extension --convert-links --page-requisites --no-host-directories ${TOPICURL}
}

do_install () {
    install -d ${D}/${installdir}/ti-docs-tree

    htmlfiles=`ls ${WORKDIR}/${P}/${TOPICFILE}/index.php/*.html`
    post-process-tiwiki.pl ${TOPICURL} $htmlfiles
    htmldoc --webpage -f ${D}/${installdir}/ti-docs-tree/${TOPICFILE}.pdf $htmlfiles
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
