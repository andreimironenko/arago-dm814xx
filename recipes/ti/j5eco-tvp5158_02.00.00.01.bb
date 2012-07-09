DESCRIPTION = "J5Eco TVP5158 Init App"
LICENSE = "TI Proprietary"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

SRC_URI = "http://install.source.dir.local/j5eco-tvp5158_02_00_00_04.tar.gz"

SRC_URI[md5sum] = "e47ccf4b6b2afdd3a51b07fd925a572e"
SRC_URI[sha256sum] = "766fdb3b2d9f10311f58c171cedb8a12a7589b3d00e2ab70b935354d38595ad2"

S = ${WORKDIR}/j5eco-tvp5158_02_00_00_04

EXTRA_OEMAKE = "KERNEL_DIR=${STAGING_KERNEL_DIR} \
        CROSS_COMPILE=${TARGET_PREFIX} \
"

do_compile() {
    cd ${S}/TVP5158_init_app

    oe_runmake 
}


do_install() {
    install -d ${D}/${installdir}/j5eco-tvp5158
    install ${S}/TVP5158_init_app/decoder_init ${D}/${installdir}/j5eco-tvp5158
}

PACKAGES += "j5eco-tvp5158-decoder-app"
FILES_j5eco-tvp5158-decoder-app = "${installdir}/j5eco-tvp5158"
INSANE_SKIP_j5eco-tvp5158-decoder-app = True
