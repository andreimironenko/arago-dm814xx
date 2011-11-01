DESCRIPTION = "Matrix GUI Application launcher"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrix-gui-v2/"
LICENSE = "BSD MIT Apache"
SECTION = "multimedia"
PRIORITY = "optional"

PR = "r15"

INITSCRIPT_NAME = "matrix-gui-2.0"
INITSCRIPT_PARAMS = "defaults 99"

PACKAGE_ARCH = "all"

inherit update-rc.d

BRANCH ?= "master"
SRCREV = "a399ea05de87a8539c72e6c29d54c5c3aea62069"

SRC_URI = "git://gitorious.org/matrix-gui-v2/matrix-gui-v2.git;protocol=git;branch=${BRANCH} \
           file://init \
           file://php.ini"

require matrix-gui-paths.inc

S = "${WORKDIR}/git"

do_install(){
	install -d ${D}${MATRIX_BASE_DIR}
	install -d ${D}${MATRIX_WEB_DIR}
	cp -rf ${S}/* ${D}${MATRIX_WEB_DIR}

    # Install our php.ini file
    install -m 0644 ${WORKDIR}/php.ini ${D}${MATRIX_BASE_DIR}/

    # Set the proper path in the init script
    sed -i -e s=__MATRIX_WEB_DIR__=${MATRIX_WEB_DIR}= ${WORKDIR}/init

	install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/matrix-gui-2.0
}

RDEPENDS_${PN} += "lighttpd lighttpd-module-fastcgi lighttpd-module-compress lighttpd-module-expire lighttpd-module-rewrite php php-cgi php-cli matrix-gui-browser"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"
