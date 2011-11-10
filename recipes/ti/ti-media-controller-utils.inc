DESCRIPTION = "TI Media Controller Utilities"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

DEPENDS = "ti-syslink"

INC_PR = "r0"

SRC_URI = "svn://gforge.ti.com/svn/m_c_utils/;module=trunk;proto=https;user=anonymous;pswd='' "

S = "${WORKDIR}/media_controller_utils_${PV}"

PLATFORM_ti816x = "ti816x"
PLATFORM_ti814x = "ti814x"
PLATFORM ?= "<UNDEFINED-PLATFORM>"

EXTRA_OEMAKE = "SYSLINK_INSTALL_DIR=${SYSLINK_INSTALL_DIR} \
                PLATFORM=${PLATFORM} \
"

do_install() {
    cd ${S}
    oe_runmake DESTDIR=${D} prefix=${prefix} install

	install -d ${D}${sysconfdir}/modprobe.d/
    install ${S}/src/linux/${PLATFORM}/modprobe.conf ${D}${sysconfdir}/modprobe.d/media-controller-utils.conf
}

FILES_${PN} = "${bindir}/* ${installdir}/ti-media-controller-utils/* ${sysconfdir}/*"
