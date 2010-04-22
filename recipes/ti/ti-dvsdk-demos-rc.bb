DESCRIPTION = "Provide init rc script for dvsdk demo"
SECTION = "multimedia"
LICENSE = "MIT"

inherit update-rc.d

RDEPENDS = "ti-dvsdk-demos"

PR = "r1"

SRC_URI = "file://dvsdk-demo-rc.sh"

INITSCRIPT_NAME = "dvsdk-demo"
INITSCRIPT_PARAMS = "start 30 5 2 . stop 40 0 1 6 ."

do_install() {
    	install -d ${D}${sysconfdir}/init.d/
	install -m 0755  ${WORKDIR}/dvsdk-demo-rc.sh ${D}${sysconfdir}/init.d/dvsdk-demo
}

