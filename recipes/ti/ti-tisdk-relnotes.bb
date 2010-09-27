DESCRIPTION = "DVSDK 4.00 Release Notes"
LICENSE = "CC-BY-SA"

PR="r3"

DEPENDS = "ti-post-process-wiki-native"

RELNOTESTOPIC_dm365 = "TMS320DM365_DVSDK_4.00_Release_Notes"
RELNOTESTOPIC_omapl138 = "OMAPL138_DVSDK_4.00_Release_Notes"
RELNOTESTOPIC_omap3evm = "OMAP3530_DVSDK_4.00_Release_Notes"
RELNOTESTOPIC_dm37x-evm = "TMS320DM3730_DVSDK_4.00_Release_Notes"
RELNOTESTOPIC_omap3evm = "OMAP3530_DVSDK_4.00_Release_Notes"
RELNOTESTOPIC_c6a816x-evm = "C6A816x_EZSDK_5.00_Release_Notes"

RELNOTESURL = "http://ap-fpdsp-swapps.dal.design.ti.com/index.php/${RELNOTESTOPIC}"

SDK_SHORT_PLATFORM_dm365 = "dm365"
SDK_SHORT_PLATFORM_omapl138 = "omapl138"
SDK_SHORT_PLATFORM_omap3evm = "omap3530"
SDK_SHORT_PLATFORM_dm37x-evm = "dm3730"
SDK_SHORT_PLATFORM_c6a816x-evm = "omap3530"

require ti-paths.inc

do_fetch () {
    mkdir -p ${WORKDIR}/${P}
    cd ${WORKDIR}/${P}

    wget --directory-prefix=${WORKDIR}/${P}/${RELNOTESTOPIC} --html-extension --convert-links --page-requisites --no-host-directories ${RELNOTESURL}
}

do_install () {
    if [ ! -n "${SDK_VERSION}" ]; then
        export SDK_VERSION="test_build"
    fi

    install -d ${D}/${installdir}
    htmlfiles=`ls ${WORKDIR}/${P}/${RELNOTESTOPIC}/index.php/*.html`
    post-process-tiwiki.pl ${RELNOTESURL} $htmlfiles

    TODAYS_DATE=`date | cut -d ' ' -f 2-3,6`

    for file in $htmlfiles; do
        sed -i "s/__SDK_VERSION__/${SDK_VERSION}/g" $file
        sed -i "s/__DATE__/${TODAYS_DATE}/g" $file
    done

    htmldoc --webpage -f ${D}/${installdir}/ezsdk_${SDK_VERSION}_${SDK_SHORT_PLATFORM}_Release_Notes.pdf $htmlfiles
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
