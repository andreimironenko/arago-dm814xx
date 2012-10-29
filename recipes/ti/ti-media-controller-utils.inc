DESCRIPTION = "TI Media Controller Utilities"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

DEPENDS = "ti-syslink ti-ipc ti-sysbios ti-xdctools"

INC_PR = "r0"

SRC_URI = "svn://gforge.ti.com/svn/m_c_utils/;module=trunk;proto=https;user=anonymous;pswd='' "

S = "${WORKDIR}/trunk/media_controller_utils"

PLATFORM_ti816x = "ti816x-evm"
PLATFORM_ti814x = "ti814x-evm"
PLATFORM_ti811x = "ti811x-evm"
PLATFORM ?= "<UNDEFINED-PLATFORM>"

EXTRA_OEMAKE = "PLATFORM=${PLATFORM} \
		ROOTDIR=${S} \
        bios_PATH=${SYSBIOS_INSTALL_DIR} \
		ipc_PATH=${IPC_INSTALL_DIR} \
		syslink_PATH=${SYSLINK_INSTALL_DIR} \
		xdc_PATH=${XDC_INSTALL_DIR} \
        CODEGEN_PATH_A8=${TOOLCHAIN_PATH} \
        CODEGEN_PATH_DSPELF=${CODEGEN_INSTALL_DIR} \
"

#AM: URI is not working. I've got this package from EZSDK.
#So we don't need do_fetch()
do_fetch() {
 :
 
 }


do_install() {
    cd ${S}
    oe_runmake DESTDIR=${D} prefix=${prefix} install

	install -d ${D}${sysconfdir}/modprobe.d/
    install ${S}/src/linux/${PLATFORM}/modprobe.conf ${D}${sysconfdir}/modprobe.d/media-controller-utils.conf

    install -d ${D}${MCUTILS_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${MCUTILS_INSTALL_DIR_RECIPE}
}

FILES_${PN} = "${bindir}/* ${installdir}/ti-media-controller-utils/* ${sysconfdir}/*"
INSANE_SKIP_${PN} = True
