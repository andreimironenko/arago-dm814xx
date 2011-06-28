DESCRIPTION = "Audio/Video files for use with the AMSDK"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_multimedia/"
LICENSE = "CC-BY-NC-ND-3.0"
SECTION = "multimedia"
PRIORITY = "optional"

PR = "r2"

COMPATIBLE_MACHINE = "(omap3evm|am37x-evm|am3517-evm|am389x-evm|beagleboard)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

VIDEO_FILES = "video_480p "
VIDEO_FILES_append_am37x-evm = "video_vga_r"
VIDEO_FILES_append_beagleboard = "video_vga"
VIDEO_FILES_append_am3517-evm = "video_wqvga"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/564/4790/amsdk-av-files.tar.gz"

S = "${WORKDIR}/amsdk-av-files"

do_compile() {
    :
}

do_install() {
    install -d ${D}${datadir}/ti \
       ${D}${datadir}/ti/audio \
       ${D}${datadir}/ti/video
    install -m 0644 ${S}/audio/* ${D}${datadir}/ti/audio/
    for file in ${VIDEO_FILES}; do
	install -m 0644 ${S}/${file}/* ${D}${datadir}/ti/video/
    done
}

FILES_${PN} += "${datadir}/ti/*"

SRC_URI[md5sum] = "8174bed98f4428b801deca8ac2258089"
SRC_URI[sha256sum] = "72bacd04430b24b0b4581e2b2e4bcdf1cb63fda1ba43036b889f5f69cb5f7c5b"

