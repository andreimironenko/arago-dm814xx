DESCRIPTION = "Software Developers Guide"
LICENSE = "CC-BY-SA"

require ti-paths.inc

DEPENDS = "ti-post-process-wiki-native"

PR = "r3"

COMPATIBLE_MACHINE = "(dm365-evm|da850-omapl138-evm|dm37x-evm|omap3evm|c6a816x-evm)"

TOPIC_dm365 = "TMS320DM365_Software_Developers_Guide"
TOPIC_omapl138 = "OMAPL138_Software_Developers_Guide"
TOPIC_dm37x-evm = "TMS320DM3730_Software_Developers_Guide"
TOPIC_omap3evm = "OMAP3530_Software_Developers_Guide"
TOPIC_c6a816x-evm = "C6A816x_AM389x_Software_Developers_Guide"

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
