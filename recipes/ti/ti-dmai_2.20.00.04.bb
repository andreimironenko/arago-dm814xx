require ti-dmai.inc

PV = "2_20_00_04"
PR = "r0"
 
COMPATIBLE_MACHINE = "(da850-omapl138-evm|da830-omapl137-evm)"

SRC_URI_append = " \
	file://doxygen_templates.tar.gz \
        file://arago-tdox \
"

SRCREV         = "500"
DMAIBRANCH     = "tags/TAG_2_20_00_04"

do_install_prepend () {
    find ${S} -name .svn -type d | xargs rm -rf
    cp -pPrf ${WORKDIR}/doxygen_templates ${S}
    cp -pPrf ${WORKDIR}/arago-tdox ${S}/tdox
    chmod a+x ${S}/release.sh
    chmod a+x ${S}/tdox
    ${S}/release.sh ${PV}
}

