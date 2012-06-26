DESCRIPTION = "J5Eco TVP5158 Init App"
LICENSE = "TI Proprietary"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

SRC_URI = "http://install.source.dir.local/j5eco-tvp5158_02_00_00_01.tar.gz"

SRC_URI[md5sum] = "ef44ef20d10d312b23ce910fe604d242"
SRC_URI[sha256sum] = "9237616e2f201a2d9363892669047bc934e104d3162183f5d99c6aad3e23625c"

S = ${WORKDIR}/j5eco-tvp5158_02_00_00_01

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
