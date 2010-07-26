require ti-dmai.inc

PV = "2_05_00_17"
PR = "r3"
 
COMPATIBLE_MACHINE = "(omap3evm|dm3730-am3715-evm)"

SRC_URI_append = " \
	file://doxygen_templates.tar.gz \
        file://arago-tdox \
"

SRCREV         = "526"
DMAIBRANCH     = "tags/TAG_${PV}"

do_install_prepend () {
    find ${S} -name .svn -type d | xargs rm -rf
    cp -pPrf ${WORKDIR}/doxygen_templates ${S}
    cp -pPrf ${WORKDIR}/arago-tdox ${S}/tdox
    chmod a+x ${S}/release.sh
    chmod a+x ${S}/tdox
    ${S}/release.sh ${PV}
}

