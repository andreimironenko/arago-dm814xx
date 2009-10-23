require ti-framework-components.inc

# compile time dependencies
DEPENDS += "ti-xdctools-native ti-xdais ti-linuxutils"
DEPENDS_dm6467t-evm  += "ti-edma3-lld"
DEPENDS_dm6467-evm  += "ti-edma3-lld"

do_compile () {
        echo "! Do not rebuild for now !"
}

# stage tree - other packages may need this
do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"

