DESCRIPTION = "TI Media Controller Utilities"
SECTION = "devel"
LICENSE = "BSD"

require ti-paths.inc
require ti-staging.inc

SRC_URI = "http://install.source.dir.local/media_controller_utils_${PV}.tar.gz"

DEPENDS = "ti-omx-libs ti-syslink"

S = "${WORKDIR}/media_controller_utils_${PV}"

PLATFORM_ti816x = "ti816x"
PLATFORM ?= "<UNDEFINED-PLATFORM>"

EXTRA_OEMAKE = "SYSLINK_INSTALL_DIR=${SYSLINK_INSTALL_DIR} \
                FIRMWARE=${OMX_INSTALL_DIR}/firmware/dm816xbm/bin/ti816x-evm \
                PLATFORM=${PLATFORM} \
"

do_install() {
    cd ${S}
    oe_runmake DESTDIR=${D} prefix=${prefix} install
}

FILES_${PN} = "${bindir}/* ${installdir}/ti-media-controller-utils/*"
