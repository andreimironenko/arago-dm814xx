DESCRIPTION = "J5Eco TVP5158 Init App"
LICENSE = "TI Proprietary"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "ti811x"

SRC_URI = "http://install.source.dir.local/j5eco-tvp5158_03_00_00_01.tar.gz \
           file://saLoopBack.c \
"
        
DEPENDS = "virtual/kernel"

SRC_URI[md5sum] = "b4d3f67e01f4d1b98ee6d4ffa4c7bd4e"
SRC_URI[sha256sum] = "7e24b33162def26beb979e6f8563cc3a222fa689120c1a5d6d017fc7c843572e"

S = ${WORKDIR}/j5eco-tvp5158_03_00_00_01

EXTRA_OEMAKE = "KERNEL_DIR=${STAGING_KERNEL_DIR} \
        CROSS_COMPILE=${TARGET_PREFIX} \
"

do_compile() {

    cd ${S}/TVP5158_init_app
    oe_runmake

    cd ${S}/video_capture_app
    oe_runmake
}


do_install() {
    install -d ${D}/${installdir}/j5eco-tvp5158
    install ${S}/TVP5158_init_app/decoder_init ${D}/${installdir}/j5eco-tvp5158

    install ${S}/video_capture_app/saLoopBack ${D}/${installdir}/j5eco-tvp5158
}

do_copyimage() {
    cp -f ${WORKDIR}/saLoopBack.c ${S}/video_capture_app/saLoopBack.c
}
addtask copyimage after do_unpack before do_patch

PACKAGES += "j5eco-tvp5158-decoder-app"
FILES_j5eco-tvp5158-decoder-app = "${installdir}/j5eco-tvp5158"
INSANE_SKIP_j5eco-tvp5158-decoder-app = True
