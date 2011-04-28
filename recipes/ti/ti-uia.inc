DESCRIPTION = "TI Unified Instrumentation Architecture for ARM/DSP Devices"
SECTION = "devel"
LICENSE = "BSD" 

require ti-paths.inc
require ti-staging.inc

PR = "r1"
PVExtra = ""

S = "${WORKDIR}/uia_${PV}${PVExtra}"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/UIA/${PV}/exports/uia_${PV}${PVExtra}.zip;name=uia"

DEPENDS = "ti-syslink ti-ipc"

EXTRA_OEMAKE = "SYSLINK=${SYSLINK_INSTALL_DIR} \
        IPC=${IPC_INSTALL_DIR}/packages \
        CC='${CC}' \
        RANLIB='${RANLIB}' \
"

UIAPLATFORM_ti816x = "evmti816x"
UIAPLATFORM        = "<Undefined Platform>"

do_compile() {
    cd ${S}/packages/ti/uia/linux
    oe_runmake

    cd ${S}/packages/ti/uia/examples/${UIAPLATFORM}
    oe_runmake
}

do_install() {
    install -d ${D}${installdir}/ti-uia
    install ${S}/packages/ti/uia/examples/${UIAPLATFORM}/bin/*.out ${D}${installdir}/ti-uia

    install -d ${D}${UIA_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${UIA_INSTALL_DIR_RECIPE}
}

FILES_${PN} = "${installdir}/ti-uia/*"
INSANE_SKIP_${PN} = True
