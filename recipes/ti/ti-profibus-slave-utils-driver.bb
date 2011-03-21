DESCRIPTION = "Builds Profibus Utility module used by Profibus Slave Application"
HOMEPAGE = "https://gforge.ti.com/gf/project/pru_sw/"
LICENSE = "GPLv2"
PRIORITY = "optional"

COMPATIBLE_MACHINE = "am181x-evm"

SRCREV = "20"
SRC_URI = "svn://gforge.ti.com/svn/pru_sw/;module=trunk;proto=https;user=anonymous;pswd=''"

S = "${WORKDIR}/trunk/peripheral_lib/profibus_hwutils/module"

inherit module
PR = "${MACHINE_KERNEL_PR}+svnr${SRCPV}"

PACKAGE_STRIP = "no"

do_install () {
        install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/pru
        install -m 0755 ${S}/profibus_hwinit.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/pru
}

FILES_${PN} = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/pru/profibus_hwinit.ko"
