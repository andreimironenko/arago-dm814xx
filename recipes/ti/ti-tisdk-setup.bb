DESCRIPTION = "Package contain Makefile and Rule.make used for building DVSDK components"
LICENSE = "TI"

require ti-paths.inc

UBOOT_ENV_dm365 = "setup-uboot-env-dm365.sh"
UBOOT_ENV_omapl138 = "setup-uboot-env-omapl138.sh"
UBOOT_ENV_dm37x-evm = "setup-uboot-env-dm3730.sh"
UBOOT_ENV_omap3evm = "setup-uboot-env-omap3530.sh"

SRC_URI = "\
	file://setup/setup.sh \
  	file://setup/common.sh \
  	file://setup/setup-host-check.sh \
  	file://setup/setup-minicom.sh \
  	file://setup/setup-package-install.sh \
  	file://setup/setup-targetfs-nfs.sh \
  	file://setup/setup-tftp.sh \
  	file://setup/setup-uboot-env-dm365.sh \
  	file://setup/setup-uboot-env-omapl138.sh \
  	file://setup/setup-uboot-env-dm3730.sh \
  	file://setup/setup-uboot-env-omap3530.sh \
"
PR = "r13"

do_install () {
	install -d ${D}/${installdir}
        cp -pPf ${WORKDIR}/setup/setup.sh ${D}/${installdir} 
	install -d ${D}/${installdir}/bin
        cp -pPf ${WORKDIR}/setup/common.sh ${D}/${installdir}/bin
        cp -pPf ${WORKDIR}/setup/setup-host-check.sh ${D}/${installdir}/bin
        cp -pPf ${WORKDIR}/setup/setup-minicom.sh ${D}/${installdir}/bin
        cp -pPf ${WORKDIR}/setup/setup-package-install.sh ${D}/${installdir}/bin
        cp -pPf ${WORKDIR}/setup/setup-targetfs-nfs.sh ${D}/${installdir}/bin
        cp -pPf ${WORKDIR}/setup/setup-tftp.sh ${D}/${installdir}/bin
        cp -pPf ${WORKDIR}/setup/${UBOOT_ENV} ${D}/${installdir}/bin/setup-uboot-env.sh
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
