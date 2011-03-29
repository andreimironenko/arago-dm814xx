DESCRIPTION = "TI TI816x HDVPSS Display Utilities"
SECTION = "devel"
LICENSE = "GPLv2/TI"

require ti-paths.inc
require ti-staging.inc

inherit update-rc.d

PR = "r6"

SRC_URI = "\
           http://install.source.dir.local/hdvpss_display_utils_${PV}.tar.gz;name=hdvpss \
"

DEPENDS = "ti-syslink"

PROVIDES += "ti-hdvpss-display-utils-loader"

S = "${WORKDIR}/hdvpss_display_utils_${PV}"

INITSCRIPT_NAME = "load-firmware.sh"
INITSCRIPT_PARAMS = "defaults 15"

PLATFORM_ti816x = "ti816x"
PLATFORM_ti814x = "ti814x"
PLATFORM ?= "<UNDEFINED-PLATFORM>"

do_compile() {
    cd ${S}/firmware
    make \
        SYSLINK_INSTALL_DIR="${SYSLINK_INSTALL_DIR}" \
        CSTOOL_PREFIX="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}" \
        all
}

do_install() {
    # Install the firmware loader on the filesystem 
    install -d ${D}/${installdir}/ti-hdvpss-display-utils
    install -m 0755 ${S}/firmware/firmware_loader ${D}/${installdir}/ti-hdvpss-display-utils/
    install -m 0755 ${S}/firmware/${PLATFORM}/*.xem3 ${D}/${installdir}/ti-hdvpss-display-utils/
    install -m 0755 ${S}/firmware/${PLATFORM}/change_resolution.sh ${D}/${installdir}/ti-hdvpss-display-utils/

    install -d ${D}/${installdir}/ti-hdvpss-display-utils/src
    install -m 0755 ${S}/firmware/*.c ${D}/${installdir}/ti-hdvpss-display-utils/src
    install -m 0755 ${S}/firmware/Makefile ${D}/${installdir}/ti-hdvpss-display-utils/src
  
    # Install the init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/firmware/${PLATFORM}/load-firmware.sh ${D}${sysconfdir}/init.d/
}

PACKAGES += "ti-hdvpss-display-utils-loader"
FILES_ti-hdvpss-display-utils-loader = "${installdir}/ti-hdvpss-display-utils/*"
INSANE_SKIP_ti-hdvpss-display-utils-loader = True

