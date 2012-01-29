DESCRIPTION = "Firmware files for use with TI wl12xx"
LICENSE = "TI TSPA"
# This recipe overlays the recipe in OE as the ti wl12xx firmware files are fetched from 
# a different git in github. 

PR = "r3"
PR_append = "+gitr${SRCREV}"
SRCREV = "c18c1ab06c8c1f9c7a3f635e9392e56d5499df6e"

SRC_URI = "git://github.com/TI-ECS/ti-utils.git;protocol=git"

# Default NVS file for out of box startup
SRC_URI += "file://wl1271-nvs.bin"

S = "${WORKDIR}/git/firmware"

do_install() {
	install -d  ${D}/lib/firmware/ti-connectivity
    	install -m 0755 ${S}/../../wl1271-nvs.bin ${D}/lib/firmware/ti-connectivity
	cp -RpP ${S}/* ${D}/lib/firmware/ti-connectivity/
}

PACKAGES =+ "${PN}-wl12xx"
FILES_${PN}-wl12xx += "/lib/firmware/ti-connectivity/*"
FILES_${PN} += "/lib/firmware/ti-connectivity/wl1271-nvs.bin"
CONFFILES_${PN} += "/lib/firmware/ti-connectivity/wl1271-nvs.bin"

PACKAGE_ARCH = "all"

