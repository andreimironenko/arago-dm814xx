PR_append = "-arago1"
 
WL12XX_FIRMWARE_RELEASE = "r3-m1-rc2"

TAG = "${@'${WL12XX_FIRMWARE_RELEASE}'.replace('-', '')}"
PV = "0.${TAG}"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/557/4631/ti-linux-firmware-wl12xx-${WL12XX_FIRMWARE_RELEASE}.tar.gz"

S = "${WORKDIR}/firmware-files"

do_install() {
	install -d  ${D}/lib/firmware/ti-connectivity
	cp -RpP ${S}/* ${D}/lib/firmware/ti-connectivity/
}

FILES_${PN}-wl12xx += "/lib/firmware/ti-connectivity/*"

SRC_URI[md5sum] = "717c8a53d58884741dd3f0c276e777f9"
SRC_URI[sha256sum] ="49cbe844ad40502ce5cefc94b1949ade0cf0ab0fd704d35bdcad85fc65d56a2a"

