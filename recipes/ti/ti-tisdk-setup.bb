DESCRIPTION = "Package containing scripts to setup the development host and target board"
LICENSE = "TI"

COMPATIBLE_MACHINE = "(omap3evm|am37x-evm|dm37x-evm|dm365-evm|omapl138|am3517-evm)"

UBOOT_ENV_dm365 = "setup-uboot-env-dm365.sh"
UBOOT_ENV_omapl138 = "setup-uboot-env-omapl138.sh"
UBOOT_ENV_dm37x-evm = "setup-uboot-env-dm3730.sh"
UBOOT_ENV_omap3evm = "setup-uboot-env-omap3530.sh"
UBOOT_ENV_am37x-evm = "setup-uboot-env-am37x.sh"
UBOOT_ENV_am3517-evm = "setup-uboot-env-am37x.sh"

SRC_URI = "\
	file://setup.sh \
  	file://common.sh \
  	file://setup-host-check.sh \
  	file://setup-minicom.sh \
  	file://setup-package-install.sh \
  	file://setup-targetfs-nfs.sh \
  	file://setup-tftp.sh \
    file://${UBOOT_ENV} \
"

PR = "r22"

do_install () {
    cp -pPf ${WORKDIR}/setup.sh ${D}/
	install -d ${D}/bin
    cp -pPf ${WORKDIR}/common.sh ${D}/bin
    cp -pPf ${WORKDIR}/setup-host-check.sh ${D}/bin
    cp -pPf ${WORKDIR}/setup-minicom.sh ${D}/bin
    cp -pPf ${WORKDIR}/setup-package-install.sh ${D}/bin
    cp -pPf ${WORKDIR}/setup-targetfs-nfs.sh ${D}/bin
    cp -pPf ${WORKDIR}/setup-tftp.sh ${D}/bin
    cp -pPf ${WORKDIR}/${UBOOT_ENV} ${D}/bin/setup-uboot-env.sh
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} += "setup.sh"
