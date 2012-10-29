DESCRIPTION = "TI Unified Instrumentation Architecture for ARM/DSP Devices"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "(ti816x|ti814x|ti811x)"

PR = "r1"
PVExtra = ""

S = "${WORKDIR}/uia_${PV}${PVExtra}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/uia/${PV}/exports/uia_${PV}${PVExtra}.zip;name=uia"

DEPENDS = "ti-syslink ti-ipc"

UIAPLATFORM_ti816x = "evmti816x"
UIAPLATFORM_ti814x = "evmti816x"
UIAPLATFORM_ti811x = "evmti816x"

#AM: URI is not working. The package can only be downloaded manually.
#It's stored in downloads.
do_fetch() {
 :
 
 }

do_compile() {
    :
}

do_install() {
    install -d ${D}${installdir}/ti-uia
    install ${S}/packages/ti/uia/examples/${UIAPLATFORM}/bin/*.out ${D}${installdir}/ti-uia

    install -d ${D}${UIA_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${UIA_INSTALL_DIR_RECIPE}
}

FILES_${PN} = "${installdir}/ti-uia/*"
INSANE_SKIP_${PN} = True
