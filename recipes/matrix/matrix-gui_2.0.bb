DESCRIPTION = "Matrix GUI Application launcher"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrix-gui-v2/"
LICENSE = "BSD MIT Apache"
SECTION = "multimedia"
PRIORITY = "optional"

PR = "r6"

INITSCRIPT_NAME = "matrix-gui-2.0"
INITSCRIPT_PARAMS = "defaults 99"

PACKAGE_ARCH = "all"

inherit update-rc.d

BRANCH ?= "master"
SRCREV = "9f5d27fec216f223c43b48fa146edf4a4bb7aa24"

SRC_URI = "git://gitorious.org/matrix-gui-v2/matrix-gui-v2.git;protocol=git;branch=${BRANCH} \
           file://init"

require matrix-gui-paths.inc

S = "${WORKDIR}/git"

do_install(){
	install -d ${D}${MATRIX_BASE_DIR}
	cp -rf ${S}/* ${D}${MATRIX_BASE_DIR}

    # Set the proper path in the init script
    sed -i -e s=__MATRIX_BASE_DIR__=${MATRIX_BASE_DIR}= ${WORKDIR}/init

	install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/matrix-gui-2.0
}

RDEPENDS_${PN} += nodejs

FILES_${PN} += "${MATRIX_BASE_DIR}/*"
