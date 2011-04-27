DESCRIPTION = "TI Media Controller HDVPSS Firmware Initscript"
SECTION = "devel"
LICENSE = "BSD"

inherit update-rc.d

PR = "r1"

SRC_URI = "http://install.source.dir.local/media_controller_utils_${PV}.tar.gz"

DEPENDS = "ti-media-controller-utils"

INITSCRIPT_NAME = "load-hdvpss-firmware.sh"
INITSCRIPT_PARAMS = "defaults 15"

PLATFORM_ti816x = "ti816x"
PLATFORM ?= "<UNDEFINED-PLATFORM>"

S = "${WORKDIR}/media_controller_utils_${PV}/${PLATFORM}"

do_install() {
    # Install the init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/load-hdvpss-firmware.sh ${D}${sysconfdir}/init.d/
}

FILES_${PN} = "${sysconfdir}/*"
