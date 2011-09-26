PR_append = "-arago2"
 
WL12XX_FIRMWARE_RELEASE = "r4-10"

TAG = "${@'${WL12XX_FIRMWARE_RELEASE}'.replace('-', '')}"
PV = "0.${TAG}"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/652/5021/ti-linux-firmware-wl12xx-${WL12XX_FIRMWARE_RELEASE}.tar.gz"

S = "${WORKDIR}/firmware-files"

do_install() {
	install -d  ${D}/lib/firmware/ti-connectivity
	cp -RpP ${S}/* ${D}/lib/firmware/ti-connectivity/
}

FILES_${PN}-wl12xx += "/lib/firmware/ti-connectivity/*"

SRC_URI[md5sum] = "29d76f04dc331ac076dc5a09da7b0918"
SRC_URI[sha256sum] ="5a978f6ba4e6a2f5609287eadf44d1925d60e07ca452f6aac8fbe535cda01522"

