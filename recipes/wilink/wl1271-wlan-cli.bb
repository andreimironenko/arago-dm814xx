DESCRIPTION = "A demo application for wilink on WL1271 chipset"
SECTION = "network"
LICENSE = "GPLv2 BSD"
DEPENDS += "virtual/kernel perl-native wpa-supplicant openssl"
RDEPENDS += "wpa-supplicant openssl"

PR = "r0"

PACKAGE_ARCH = ${MACHINE_ARCH}

COMPATIBLE_MACHINE = "am37x-evm|am180x-evm"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/ecs/WL1271_Linux_SDK/AM18x/exports/3.20.00.13_M6.01/wl1271-demos.tar.gz"

S = "${WORKDIR}/wl1271-demos"

PLATFORM_am37x-evm = "omap3evm"
PLATFORM_am180x-evm = "am1808"
PLATFORM ?= "UNKNOWN"

do_compile() {
	# Build WiLink CLI application
	cd ${S}/CUDK && make \
	HOST_PLATFORM="${PLATFORM}" \
	KERNEL_DIR="${STAGING_KERNEL_DIR}" \
	CROSS_COMPILE="${TARGET_PREFIX}" \
	ARCH="arm" \
	XCC="n" \
	BUILD_SUPPL="y" \
	TI_SUPP_LIB_DIR="${STAGING_INCDIR}" \
	BASE_BUILDOS="${STAGING_KERNEL_DIR}"
}

do_install () {
	install -d ${D}${libdir}
	install -d ${D}${libdir}/firmware/
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/wpa_supplicant
	install -d ${D}${datadir}
	install -d ${D}${datadir}/wl1271-demos
	install -d ${D}${datadir}/wl1271-demos/wlan

	install -m 0755 ${S}/WLAN_firmware/firmware.bin ${D}${libdir}/firmware/
	install -m 0755 ${S}/CUDK/output/tiwlan.ini ${D}${libdir}/firmware/
	install -m 0755 ${S}/CUDK/output/tiwlan_loader ${D}${bindir}
	install -m 0755 ${S}/CUDK/output/wlan_cu ${D}${bindir}
	install -m 0755 ${S}/CUDK/output/wpa_supplicant.txt ${D}${sysconfdir}/wpa_supplicant
	install -m 0755 ${S}/script/${PLATFORM}/install-wlan.sh ${D}${datadir}/wl1271-demos/wlan
}

FILES_${PN} = " \
	${libdir}/* \
	${sysconfdir}/* \
	${datadir}/* \
	${bindir}/* \
	"

PACKAGE_STRIP = "no"

SRC_URI[md5sum] = "f9aae8a78fa6d9c1a31a4cdc3d3af7fe"
SRC_URI[sha256sum] = "7081da34338cb7d0318ef98bfb62cd1ebc17c3f919c5a830b22b4ae2ee27100c"
