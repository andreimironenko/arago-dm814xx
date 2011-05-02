DESCRIPTION = "Software Developers Guide"
LICENSE = "CC-BY-SA"

require ti-paths.inc

PR = "r4"

COMPATIBLE_MACHINE = "(dm365-evm|da850-omapl138-evm|dm37x-evm|omap3evm|ti816x|ti814x)"

TOPIC_dm365 = "TMS320DM365_Software_Developers_Guide"
TOPIC_dm368-evm = "TMS320DM368_Software_Developers_Guide"
TOPIC_omapl138 = "OMAPL138_Software_Developers_Guide"
TOPIC_dm37x-evm = "TMS320DM3730_Software_Developers_Guide"
TOPIC_omap3evm = "OMAP3530_Software_Developers_Guide"
TOPIC_c6a816x-evm = "C6A816x_AM389x_EZ_Software_Developers_Guide"
TOPIC_dm816x-evm = "DM816x_C6A816x_AM389x_EZ_Software_Developers_Guide"
TOPIC_dm816x-custom = "DM816x_C6A816x_AM389x_EZ_Software_Developers_Guide"
TOPIC_c6a814x-evm = "C6A814x_AM387x_EZ_Software_Developers_Guide"
TOPIC_dm814x-evm = "DM814x_C6A814x_AM387x_EZ_Software_Developers_Guide"
TOPIC_dm814x-custom = "DM814x_C6A814x_AM387x_EZ_Software_Developers_Guide"

TOPICFILE = "${@bb.data.getVar('TOPIC', d, 1).replace('/','_')}"
TOPICURL = "http://ap-fpdsp-swapps.dal.design.ti.com/index.php/${TOPIC}"

SRC_URI = "file://post-process-tiwiki.pl"

do_fetchwiki () {
    mkdir -p ${WORKDIR}/${P}
    cd ${WORKDIR}/${P}

    wget --directory-prefix=${WORKDIR}/${P}/${TOPICFILE} --html-extension --convert-links --page-requisites --no-host-directories ${TOPICURL}
}

addtask fetchwiki after do_fetch before do_install

do_install () {
    install -d ${D}/${installdir}/ti-docs-tree

    htmlfiles=`ls ${WORKDIR}/${P}/${TOPICFILE}/index.php/*.html`
    ${WORKDIR}/post-process-tiwiki.pl ${TOPICURL} $htmlfiles
    htmldoc --webpage -f ${D}/${installdir}/ti-docs-tree/${TOPICFILE}.pdf $htmlfiles
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
