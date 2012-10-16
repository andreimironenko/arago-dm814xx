DESCRIPTION = "Video Loop Back Demo App"
LICENSE = "TI Proprietary"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

SRC_URI = "http://install.source.dir.local/videoloopbackdemo_01_00_00_01.tar.gz \
"
        
DEPENDS = "virtual/kernel"

SRC_URI[md5sum] = "fbb3daffcc6ec2e33b15b3db87b2f5bc"
SRC_URI[sha256sum] = "f0ee8553ef8210a9d119991a3c17f16aa4c7fe6c403b889fc420253c6e60ef0f"

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
