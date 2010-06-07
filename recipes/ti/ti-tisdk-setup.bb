DESCRIPTION = "Package contain Makefile and Rule.make used for building DVSDK components"
LICENSE = "TI"

require ti-paths.inc

SRC_URI = "\
	file://setup/setup.sh \
  	file://setup/common.sh \
  	file://setup/setup-host-check.sh \
  	file://setup/setup-minicom.sh \
  	file://setup/setup-package-install.sh \
  	file://setup/setup-targetfs-nfs.sh \
  	file://setup/setup-tftp.sh \
  	file://setup/setup-uboot-env.sh \
"
PR = "r0"

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
        cp -pPf ${WORKDIR}/setup/setup-uboot-env.sh ${D}/${installdir}/bin
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
