DESCRIPTION = "Builds Crypto module used by OCF-Linux driver in OpenSSL example applications"
HOMEPAGE = "https://gforge.ti.com/gf/project/arm_crypto/"
LICENSE = "BSD GPLv2"

COMPATIBLE_MACHINE = "am37x-evm|am3517-evm"

DEPENDS += "virtual/kernel"

SRC_URI = "svn://gforge.ti.com/svn/arm_crypto/;module=trunk;proto=https;user=anonymous;pswd=''"

#gforge source revision
SRCREV = "16"

PACKAGE_STRIP = "no"

S = "${WORKDIR}/trunk"

inherit module

MACHINE_KERNEL_PR_append = "+svnr${SRCPV}"

do_compile_prepend () {
	sed -i "s/arm-none-linux-gnueabi-/${TARGET_PREFIX}/g" ${S}/Makefile
}

do_install () {
        install -d ${D}/lib/modules/${KERNEL_VERSION}/crypto/ocf/
        install -m 0755 ${S}/ocf_omap3_cryptok.ko ${D}/lib/modules/${KERNEL_VERSION}/crypto/ocf/
}

FILES_${PN} = "/lib/modules/${KERNEL_VERSION}/crypto/ocf/ocf_omap3_cryptok.ko"
