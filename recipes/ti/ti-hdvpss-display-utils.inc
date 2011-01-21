DESCRIPTION = "TI TI816x HDVPSS Display Utilities"
SECTION = "devel"
LICENSE = "GPLv2/TI"

require ti-paths.inc
require ti-staging.inc

inherit update-rc.d

PR = "r5"

SRC_URI = "\
           http://install.source.dir.local/hdvpss_display_utils_${PV}.tar.gz;name=hdvpss \
"

DEPENDS = "ti-syslink"

PROVIDES = "ti-hdvpss-display-utils-module"
PROVIDES += "ti-hdvpss-display-utils-loader"

S = "${WORKDIR}/hdvpss_display_utils_${PV}"

KERNEL_VERSION = "${@base_read_file('${STAGING_KERNEL_DIR}/kernel-abiversion')}"

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
    # Install the kernel modules
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video
    install -m 0755 ${S}/kernel/${PLATFORM}/TI81xx_hdmi.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video/ 

    # Install the firmware loader on the filesystem 
    install -d ${D}/${installdir}/ti-hdvpss-display-utils
    install -m 0755 ${S}/firmware/firmware_loader ${D}/${installdir}/ti-hdvpss-display-utils/
    install -m 0755 ${S}/firmware/${PLATFORM}/*.xem3 ${D}/${installdir}/ti-hdvpss-display-utils/
    install -m 0755 ${S}/firmware/change_resolution.sh ${D}/${installdir}/ti-hdvpss-display-utils/
  
    # Install the init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/firmware/${PLATFORM}/load-firmware.sh ${D}${sysconfdir}/init.d/
}

do_install_append_ti814x() {
    install -m 0755 ${S}/kernel/${PLATFORM}/ti81xxfb.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video/ 
    install -m 0755 ${S}/kernel/${PLATFORM}/vpss.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video/ 
}

PACKAGES += "ti-hdvpss-display-utils-module"
FILES_ti-hdvpss-display-utils-module = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/video/*" 
RDEPENDS_ti-hdvpss-display-utils-module += "update-modules"

pkg_postinst_ti-hdvpss-display-utils-module () {
#!/bin/sh
if [ -n "$D" ]; then
	exit 1
fi

depmod -a
update-modules || true
}

pkg_postrm_ti-hdvpss-display-utils-module () {
#!/bin/sh
update-modules || true
}

PACKAGES += "ti-hdvpss-display-utils-loader"
RDEPENDS_ti-hdvpss-display-utils-loader_append = " ti-hdvpss-display-utils-module"
FILES_ti-hdvpss-display-utils-loader = "${installdir}/ti-hdvpss-display-utils/*"
INSANE_SKIP_ti-hdvpss-display-utils-loader = True
