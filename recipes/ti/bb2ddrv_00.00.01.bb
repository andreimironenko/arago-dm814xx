DESCRIPTION = "BB2D hardware acceleration engine driver"
LICENSE = "TI Proprietary"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "ti811x"
DEPENDS = "virtual/kernel ti-linuxutils"

SRC_URI = "http://install.source.dir.local/linux_bltsville_bb2d_00.00.01.tar.gz"

SRC_URI[md5sum] = "c8aee2fe127c86feb909d04ffa76fc8c"
SRC_URI[sha256sum] = "49b3ca03a68769b408d435ae764a243c8a85fa379d51076ad992a671cd895163"

S = ${WORKDIR}/linux_bltsville_bb2d_00.00.01

EXTRA_OEMAKE = "KERNEL_INSTALL_DIR=${STAGING_KERNEL_DIR} \
        LINUXKERNEL_INSTALL_DIR=${STAGING_KERNEL_DIR} \
        LINUXUTILS_INSTALL_DIR=${LINUXUTILS_INSTALL_DIR} \
        CSTOOL_DIR=${TOOLCHAIN_PATH} \
        BB2D_ROOTDIR=${S} \
"

do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    cd ${S}
    oe_runmake
}


do_install() {
    install -d ${D}/${installdir}/bb2ddrv
    install ${S}/driver/bb2d.ko ${D}/${installdir}/bb2ddrv
    install ${S}/bltsville/lib/libbltsville_bb2d-rel.so ${D}/${installdir}/bb2ddrv
    install ${S}/bltsville/lib/libbltsville_bb2d-dbg.so ${D}/${installdir}/bb2ddrv
    ln -sf ${D}/${installdir}/bb2ddrv/libbltsville_bb2d-dbg.so ${D}/${installdir}/bb2ddrv/libbltsville_bb2d.so    
    install ${S}/test/app/bin/bvtest ${D}/${installdir}/bb2ddrv
    install ${S}/test/app/bin/bvtest_g ${D}/${installdir}/bb2ddrv
    install ${S}/test/script/testbv.sh ${D}/${installdir}/bb2ddrv
    install -d ${D}/${installdir}/bb2ddrv/images
    install ${S}/test/images/*.png ${D}/${installdir}/bb2ddrv/images
    install ${S}/test/images/*.jpg ${D}/${installdir}/bb2ddrv/images
}

PACKAGES += "bb2ddrv-test-app"
FILES_bb2ddrv-test-app = "${installdir}/bb2ddrv"
INSANE_SKIP_bb2ddrv-test-app = True
