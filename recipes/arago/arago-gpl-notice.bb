DESCRIPTION = "Script to warn users of GPLv3 content on the target file system at boot time"
LICENSE = "MIT"

PR = "r1"

INITSCRIPT_NAME = "gplv3-notice"
INITSCRIPT_PARAMS = "defaults 99"

PACKAGE_ARCH = "all"

inherit update-rc.d

SRC_URI = "file://print-gplv3-packages.sh"

do_install(){
	install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/print-gplv3-packages.sh ${D}${sysconfdir}/init.d/gplv3-notice
}
