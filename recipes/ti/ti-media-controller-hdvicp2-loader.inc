DESCRIPTION = "TI Media Controller HDVICP2 Firmware Initscript"
SECTION = "devel"
LICENSE = "BSD"

inherit update-rc.d

PACKAGE_ARCH = "${MACHINE_ARCH}"

INC_PR = "r1"

DEPENDS = "ti-media-controller-utils"

INITSCRIPT_NAME = "load-hdvicp2-firmware.sh"
INITSCRIPT_PARAMS = "defaults 10"

PLATFORM_ti816x = "ti816x"
PLATFORM_ti814x = "ti814x"
PLATFORM ?= "<UNDEFINED-PLATFORM>"

SRC_URI = "svn://gforge.ti.com/svn/m_c_utils/;module=trunk;proto=https;user=anonymous;pswd='' "

S = "${WORKDIR}/trunk/media_controller_utils/${PLATFORM}"

do_install() {
    # Install the init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/load-hdvicp2-firmware.sh ${D}${sysconfdir}/init.d/
}

FILES_${PN} = "${sysconfdir}/*"
