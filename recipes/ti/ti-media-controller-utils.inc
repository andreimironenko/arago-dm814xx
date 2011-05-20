DESCRIPTION = "TI Media Controller Utilities"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

DEPENDS = "ti-omx-libs ti-syslink"

INC_PR = "r0"

SRC_URI = "svn://gforge.ti.com/svn/m_c_utils/;module=trunk;proto=https;user=anonymous;pswd='' "

S = "${WORKDIR}/trunk/media_controller_utils"

PLATFORM_ti816x = "ti816x"
PLATFORM ?= "<UNDEFINED-PLATFORM>"

EXTRA_OEMAKE = "SYSLINK_INSTALL_DIR=${SYSLINK_INSTALL_DIR} \
                FIRMWARE=${OMX_INSTALL_DIR}/firmware \
                PLATFORM=${PLATFORM} \
"

do_install() {
    cd ${S}
    oe_runmake DESTDIR=${D} prefix=${prefix} install
}

FILES_${PN} = "${bindir}/* ${installdir}/ti-media-controller-utils/*"
