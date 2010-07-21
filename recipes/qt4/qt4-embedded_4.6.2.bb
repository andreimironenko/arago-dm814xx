require qt4-embedded.inc

PR = "${INC_PR}.1"

QT_CONFIG_FLAGS_append_armv6 = " -no-neon "

require qt-${PV}.inc

SRC_URI_append_omap3 = "https://gforge01.dal.design.ti.com/gf/download/frsrelease/416/3753/TI-Neon-BlitEngine-Qte-Arago-v1.4.patch;name=neonpatch \
		https://gforge01.dal.design.ti.com/gf/download/frsrelease/416/3754/TI-Neon-BlitEngine-Qte-v1.4.tar.gz;name=neontarball"

CXXFLAGS_append_omap3 = " -I${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/include/ "
LDFLAGS_append_omap3 = " -L${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/lib/ -lTICPUBLT_BX "

do_install_append_omap3 () {
 	cp -ar ${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/lib/* ${D}/${libdir}/
	mkdir -p ${D}/${datadir}/ti/blitrix
	cp -ar ${WORKDIR}/TI-Neon-BlitEngine-Qte-v1.4/src/3rdparty/tiblt/demo/* ${D}/${datadir}/ti/blitrix
}

PACKAGES += "qt4-blitrix-libs "
FILES_qt4-blitrix-libs = "${libdir}/libTI*"

PACKAGES += " qt4-blitrix-demos "
FILES_qt4-blitrix-demos = "${datadir}/ti/blitrix/*"

INSANE_SKIP_qt4-blitrix-libs = True
INSANE_SKIP_qt4-blitrix-demos = True

pkg_postinst_qt4-blitrix-demos () {
 ln -s /usr/lib/libQtGuiE.so.4  /usr/lib/libQtGui.so.4
 ln -s /usr/lib/libQtCoreE.so.4  /usr/lib/libQtCore.so.4
}

SRC_URI[md5sum] = "eb651ee4b157c01696aa56777fc6e0e5"
SRC_URI[sha256sum] = "176f51ddb06dce67ab4b2efc6b327dc21ed8f764c5d97acc15ff1f907c2affae"

SRC_URI[neonpatch.md5sum] = "1084baa67d203dcd4342deb19b402331"
SRC_URI[neonpatch.sha256sum] = "aedc2fc0d3e446b2ad3aa81bfe0a4d81f6533086d3c44d521c392a34edaef711"

SRC_URI[neontarball.md5sum] = "ebc7902a21b98d06292af4caeaf5aa8c"
SRC_URI[neontarball.sha256sum] = "234f211234c40f6887cc33a0bbfc5c8c9f4ccbadc9dc37c10c98109be559a580"

