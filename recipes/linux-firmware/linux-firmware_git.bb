DESCRIPTION = "Firmware files for use with TI wl12xx"

# This recipe overlays the recipe in OE as the ti wl12xx firmware files are fetched from 
# a different git in github. 

PR = "r0"
PR_append = "+gitr${SRCREV}"
SRCREV = "RLS_R4_11"

SRC_URI = "git://github.com/TI-OpenLink/ti-utils.git;protocol=git"

S = "${WORKDIR}/git/firmware"

do_install() {
	install -d  ${D}/lib/firmware/ti-connectivity
	cp -RpP ${S}/* ${D}/lib/firmware/ti-connectivity/
}

PACKAGES =+ "${PN}-wl12xx"
FILES_${PN}-wl12xx += "/lib/firmware/ti-connectivity/*"

PACKAGE_ARCH = "all"

