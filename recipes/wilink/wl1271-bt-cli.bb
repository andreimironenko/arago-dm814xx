DESCRIPTION = "A demo application for bluetooth on WL1271 chipset"
SECTION = "network"
LICENSE = "GPLv2 BSD"
DEPENDS += "openobex"
RDEPENDS += "bluez4 openobex ussp-push obexftp bluez-hcidump wl1271-bt-enable"

PR = "r0"

PACKAGE_ARCH = ${MACHINE_ARCH}

COMPATIBLE_MACHINE = "am37x-evm|am180x-evm"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/ecs/WL1271_Linux_SDK/AM18x/exports/3.20.00.13_M6.01/wl1271-demos.tar.gz"

S = "${WORKDIR}/wl1271-demos"

PLATFORM_am37x-evm = "omap3evm"
PLATFORM_am180x-evm = "am1808"
PLATFORM ?= "UNKNOWN"

do_compile() {
	# Build opp server application
	cd ${S}/oppserver && ${CC} -o oppserver *.c ${LDFLAGS} ${CFLAGS} -lopenobex -lbluetooth -lmisc
}

do_install () {
	install -d ${D}${bindir}
	install -d ${D}${base_libdir}/firmware
	install -d ${D}${datadir}/wl1271-demos
	install -d ${D}${datadir}/wl1271-demos/bluetooth
	install -d ${D}${datadir}/wl1271-demos/bluetooth/gallery
	install -d ${D}${datadir}/wl1271-demos/bluetooth/scripts

	install -m 0755 ${S}/oppserver/oppserver ${D}${bindir}
	install -m 0755 ${S}/BT_firmware/${PLATFORM}/* ${D}${base_libdir}/firmware
	install -m 0755 ${S}/gallery/* ${D}${datadir}/wl1271-demos/bluetooth/gallery
	install -m 0755 ${S}/script/${PLATFORM}/bluetooth_scripts/* ${D}${datadir}/wl1271-demos/bluetooth/scripts
}

FILES_${PN} = " \
	${base_libdir}/* \
	${datadir}/* \
	${bindir}/* \
	"

SRC_URI[md5sum] = "f9aae8a78fa6d9c1a31a4cdc3d3af7fe"
SRC_URI[sha256sum] = "7081da34338cb7d0318ef98bfb62cd1ebc17c3f919c5a830b22b4ae2ee27100c"
