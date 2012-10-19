DESCRIPTION = "Video Loop Back Demo App"
LICENSE = "TI Proprietary"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

SRC_URI = "http://install.source.dir.local/videoloopbackdemo_01_00_00_01.tar.gz \
"
        
DEPENDS = "virtual/kernel"

SRC_URI[md5sum] = "9628cc01db6150320d39643d28473d1c"
SRC_URI[sha256sum] = "1604dafaf636302234d48357ca6d680c20583d0d2ce3ffbd51fe1f5f5c5f7346"

S = ${WORKDIR}/videoloopbackdemo_01_00_00_01

EXTRA_OEMAKE = "KERNEL_DIR=${STAGING_KERNEL_DIR} \
        CROSS_COMPILE=${TARGET_PREFIX} \
        CSTOOL_PREFIX=${TOOLCHAIN_PATH} \
"

inherit qt4e

do_compile() {

    cd ${S}/dspm3load
    make CSTOOL_PREFIX=${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX} all

    cd ${S}
    make PLATFORM=${PLATFORM}

    cd ${S}/vlb
    make CROSS_COMPILE=${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX} KERNEL_DIR=${WORKDIR}/../../../sysroots/c6a811x-evm-none-linux-gnueabi/kernel all

    cd ${S}/fbsetup
    make CSTOOL_PREFIX=${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX} KERNEL_INSTALL_DIR=${WORKDIR}/../../../sysroots/c6a811x-evm-none-linux-gnueabi/kernel all
}


do_install() {
    install -d ${D}/${installdir}/videoloopbackdemo
    install ${S}/vlb/vlb ${D}/${installdir}/videoloopbackdemo
    install ${S}/fbsetup/fbsetup ${D}/${installdir}/videoloopbackdemo
    install ${S}/cpu_usage ${D}/${installdir}/videoloopbackdemo
}

PACKAGES += "videoloopbackdemo-app"
FILES_videoloopbackdemo-app = "${installdir}/videoloopbackdemo"
INSANE_SKIP_videoloopbackdemo-app = True
