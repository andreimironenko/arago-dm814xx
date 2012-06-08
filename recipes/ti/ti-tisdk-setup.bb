DESCRIPTION = "Package contain Makefile and Rule.make used for building DVSDK components"
LICENSE = "TI"

require ti-paths.inc
COMPATIBLE_MACHINE = "(omap3evm|am37x-evm|dm37x-evm|dm365-evm|omapl138|am3517-evm|dm368-evm|beagleboard|ti33x|ti816x|ti814x|ti811x)"

UBOOT_ENV_dm365-evm = "setup-uboot-env-dm365.sh"
UBOOT_ENV_dm368-evm = "setup-uboot-env-dm365.sh"
UBOOT_ENV_omapl138 = "setup-uboot-env-omapl138.sh"
UBOOT_ENV_dm37x-evm = "setup-uboot-env-dm3730.sh"
UBOOT_ENV_omap3evm = "setup-uboot-env-omap3530.sh"
UBOOT_ENV_ti816x = "setup-uboot-env-ti816x.sh"
UBOOT_ENV_ti814x = "setup-uboot-env-ti814x.sh"
UBOOT_ENV_ti811x = "setup-uboot-env-ti811x.sh"
UBOOT_ENV_am37x-evm = "setup-uboot-env-am37x.sh"
UBOOT_ENV_beagleboard = "setup-uboot-env-beagleboard.sh"
UBOOT_ENV_am3517-evm = "setup-uboot-env-am3517.sh"
UBOOT_ENV_am180x-evm = "setup-uboot-env-am18x.sh"
UBOOT_ENV_am181x-evm = "setup-uboot-env-am18x.sh"
UBOOT_ENV_am389x-evm = "setup-uboot-env-am389x.sh"
UBOOT_ENV_ti33x = "setup-uboot-env-am335x.sh"

SETUP_NAND_ti816x = "setup-nand-images-dm816x.sh"
SETUP_NAND_ti814x = "setup-nand-images-dm814x.sh"
SETUP_NAND_ti811x = "setup-nand-images-c6a811x.sh"

SRC_URI = "\
	file://setup.sh \
  	file://common.sh \
  	file://setup-host-check.sh \
  	file://setup-minicom.sh \
  	file://setup-package-install.sh \
  	file://setup-targetfs-nfs.sh \
  	file://setup-tftp.sh \
    file://${UBOOT_ENV} \
    file://${SETUP_NAND} \
"

PR = "r48"

do_install () {
    install -m 0755 ${WORKDIR}/setup.sh ${D}/
	install -d ${D}/bin
    install -m 0755 ${WORKDIR}/common.sh ${D}/bin
    install -m 0755 ${WORKDIR}/setup-host-check.sh ${D}/bin
    install -m 0755 ${WORKDIR}/setup-minicom.sh ${D}/bin
    install -m 0755 ${WORKDIR}/setup-package-install.sh ${D}/bin
    install -m 0755 ${WORKDIR}/setup-targetfs-nfs.sh ${D}/bin
    install -m 0755 ${WORKDIR}/setup-tftp.sh ${D}/bin
    install -m 0755 ${WORKDIR}/${UBOOT_ENV} ${D}/bin/setup-uboot-env.sh
    install -m 0755 ${WORKDIR}/${SETUP_NAND} ${D}/bin/setup-nand-images.sh
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} += "setup.sh"
