DESCRIPTION = "DVSDK 4.00 Release Notes"
LICENSE = "CC-BY-SA"

PR="r1"

DEPENDS = "ti-post-process-wiki-native"

RELNOTESTOPIC = "DVSDK_4.00_Release_Notes"
RELNOTESURL = "http://ap-fpdsp-swapps.dal.design.ti.com/index.php/${RELNOTESTOPIC}"

SDK_PLATFORM_dm365 = "TMS320DM365"
SDK_PLATFORM_omapl138 = "TMS320DM365"

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

    for file in $htmlfiles; do
        sed -i "s/__SDK_VERSION__/${SDK_VERSION}/g" $file
        sed -i "s/__SDK_PLATFORM__/${SDK_PLATFORM}/g" $file
    done

    htmldoc --webpage -f ${D}/${installdir}/DVSDK_${SDK_VERSION}_${MACHINE_ARCH}_Release_Notes.pdf $htmlfiles
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
