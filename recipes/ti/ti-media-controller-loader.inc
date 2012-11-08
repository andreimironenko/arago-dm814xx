DESCRIPTION = "TI Media Controller HDVICP2 Firmware Initscript"
SECTION = "devel"
LICENSE = "BSD"

inherit update-rc.d

PACKAGE_ARCH = "${MACHINE_ARCH}"

INC_PR = "r1"

DEPENDS = "ti-media-controller-utils"

INITSCRIPT_NAME = "load-hd-firmware.sh"
INITSCRIPT_PARAMS = "defaults 01 99"

PLATFORM_ti816x = "ti816x-evm"
PLATFORM_ti814x = "ti814x-evm"
PLATFORM_ti811x = "ti811x-evm"
PLATFORM ?= "<UNDEFINED-PLATFORM>"

SRC_URI = "svn://gforge.ti.com/svn/m_c_utils/;module=trunk;proto=https;user=anonymous;pswd='' \
"

S = "${WORKDIR}/trunk/media_controller_utils/${PLATFORM}"

do_install() {
    # Install the init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/load-hd-firmware.sh ${D}${sysconfdir}/init.d/
}

FILES_${PN} = "${sysconfdir}/*"
INSANE_SKIP_${PN} = True
