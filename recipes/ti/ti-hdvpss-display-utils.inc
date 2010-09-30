DESCRIPTION = "TI TI816x HDVPSS Display Utilities"
SECTION = "devel"
LICENSE = "TI"

require ti-paths.inc
require ti-staging.inc

inherit update-rc.d

PR = "r1"

SRC_URI = "\
           http://install.source.dir.local/hdvpss_display_utils_${PV}.tar.gz;name=hdvpss \
           file://load-firmware.sh \
"

PROVIDES = "ti-hdvpss-display-utils-module"
PROVIDES += "ti-hdvpss-display-utils-loader"

S = "${WORKDIR}/hdvpss_display_utils_${PV}"

KERNEL_VERSION = "${@base_read_file('${STAGING_KERNEL_DIR}/kernel-abiversion')}"

INITSCRIPT_NAME = "load-firmware"
INITSCRIPT_PARAMS = "defaults 15"

do_install() {
    # Install the kernel modules
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video
    install -m 0755 ${S}/kernel/vpss.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video/ 
    install -m 0755 ${S}/kernel/ti81xxfb.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video/ 
    install -m 0755 ${S}/kernel/TI81xx_hdmi.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video/ 

    # Install the firmware loader on the filesystem 
    install -d ${D}/${installdir}/ti-hdvpss-display-utils
    install -m 0755 ${S}/firmware/m3_firmware_load.m3 ${D}/${installdir}/ti-hdvpss-display-utils/
    install -m 0755 ${S}/firmware/ti816x_hdvpss.xem3 ${D}/${installdir}/ti-hdvpss-display-utils/
  
    # Install the init script
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rcS.d
    install -d ${D}${sysconfdir}/rc0.d
    install -d ${D}${sysconfdir}/rc1.d
    install -d ${D}${sysconfdir}/rc2.d
    install -d ${D}${sysconfdir}/rc3.d
    install -d ${D}${sysconfdir}/rc4.d
    install -d ${D}${sysconfdir}/rc5.d
    install -m 0755 ${WORKDIR}/load-firmware.sh ${D}${sysconfdir}/init.d/
    ln -sf ../init.d/load-firmware.sh ${D}${sysconfdir}/rcS.d/S15firmware
    ln -sf ../init.d/load-firmware.sh ${D}${sysconfdir}/rc1.d/S15firmware
    ln -sf ../init.d/load-firmware.sh ${D}${sysconfdir}/rc2.d/S15firmware
    ln -sf ../init.d/load-firmware.sh ${D}${sysconfdir}/rc3.d/S15firmware
    ln -sf ../init.d/load-firmware.sh ${D}${sysconfdir}/rc4.d/S15firmware
    ln -sf ../init.d/load-firmware.sh ${D}${sysconfdir}/rc5.d/S15firmware
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
