DESCRIPTION = "Audio/Video files for use with the AMSDK"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_multimedia/"
LICENSE = "CC-BY-NC-ND-3.0"
SECTION = "multimedia"
PRIORITY = "optional"

PR = "r0"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/564/4701/amsdk-av-files.tar.gz"

S = "${WORKDIR}/amsdk-av-files"

do_compile() {
    :
}

do_install() {
    install -d ${D}${datadir}/ti \
       ${D}${datadir}/ti/audio \
       ${D}${datadir}/ti/video
    install -m 0644 ${S}/audio/* ${D}${datadir}/ti/audio/
    install -m 0644 ${S}/video/* ${D}${datadir}/ti/video/
}

FILES_${PN} += "${datadir}/ti/*"

SRC_URI[md5sum] = "74a86935c8730de00ce14f3bf57f3ea3"
SRC_URI[sha256sum] = "2bbff1f511baadf9a1c1a3f1e09c5c092f0692568cbed7d5f3d31e26c3caf3dd"

