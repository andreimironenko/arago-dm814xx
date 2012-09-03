DESCRIPTION = "J5Eco TVP5158 Init App"
LICENSE = "TI Proprietary"

require ti-paths.inc

COMPATIBLE_MACHINE = "ti811x"

SRC_URI = "http://install.source.dir.local/j5eco-tvp5158_02_00_00_05.tar.gz"

SRC_URI[md5sum] = "7e53b13deb9b9e72d7d8ef5c66eadac7"
SRC_URI[sha256sum] = "7ec05d86dcfad7581ce45ce1b2f77b92af13c5ac31160b11728c1e509f1a3cd7"

S = ${WORKDIR}/j5eco-tvp5158_02_00_00_05

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
