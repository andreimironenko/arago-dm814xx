DESCRIPTION = "A Client for Wi-Fi Protected Access (WPA)."
SECTION = "network"
LICENSE = "GPLv2 BSD"
HOMEPAGE = "http://hostap.epitest.fi/wpa_supplicant/"
DEPENDS = "virtual/kernel openssl"

PR = "r3-arago2"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "http://hostap.epitest.fi/releases/wpa_supplicant-${PV}.tar.gz \
	file://defconfig-openssl \
	file://ifupdown.sh \
	file://functions.sh \
"

# This patch requires that the corresponding kernel be patched with Wilink
# driver code.  This currently is only done for the am180x-evm machine
# type.  Applying this patch for other machine types will cause a compile
# failure.
SRC_URI_append_am180x-evm = " file://wpa_suppl.diff"

CFLAGS += "-DCONFIG_BACKEND_FILE -DCONFIG_DRIVER_WEXT -DCONFIG_EAP_WSC  -DTI_WLAN_DRIVER -D__BYTE_ORDER_LITTLE_ENDIAN  -DEAP_TLS -DEAP_PEAP -DEAP_TTLS \
	-DEAP_MD5 -DEAP_MSCHAPv2 -DEAP_SIM -DEAP_TLV -DIEEE8021X_EAPOL -DEAP_TLS_FUNCS -DEAP_TLS_OPENSSL -DINTERNAL_SHA256 -DCONFIG_WIRELESS_EXTENSION \
	-DCONFIG_CTRL_IFACE -DCONFIG_CTRL_IFACE_UNIX "


S = "${WORKDIR}/wpa_supplicant-${PV}"

PACKAGES_prepend = "wpa-supplicant-passphrase "
FILES_wpa-supplicant-passphrase = "/usr/sbin/wpa_passphrase"

RREPLACES = "wpa-supplicant-cli"

RRECOMMENDS_${PN} = "wpa-supplicant-passphrase"

export TOOLCHAIN_OPTIONS

do_configure () {
	install -m 0644 ${WORKDIR}/defconfig-openssl .config
}

do_compile () {
	make CROSS_COMPILE=${TARGET_PREFIX} OPENSSL_PATH="${STAGING_INCDIR}/openssl" KERNEL_DIR="${STAGING_KERNEL_DIR}"
}

do_install () {
	install -d ${D}${sbindir}
	install -m 755 wpa_supplicant ${D}${sbindir}
	install -m 755 wpa_passphrase ${D}${sbindir}
	install -m 755 wpa_cli        ${D}${sbindir}

	install -d ${D}${localstatedir}/run/wpa_supplicant
	install -d ${D}${sysconfdir}/network/if-pre-up.d/
	install -d ${D}${sysconfdir}/network/if-post-down.d/
	install -d ${D}${sysconfdir}/network/if-down.d/

	install -d ${D}${sysconfdir}/wpa_supplicant
	install -m 755 ${WORKDIR}/ifupdown.sh ${D}${sysconfdir}/wpa_supplicant/
	install -m 755 ${WORKDIR}/functions.sh ${D}${sysconfdir}/wpa_supplicant

	ln -s /etc/wpa_supplicant/ifupdown.sh ${D}${sysconfdir}/network/if-pre-up.d/wpasupplicant
	ln -s /etc/wpa_supplicant/ifupdown.sh ${D}${sysconfdir}/network/if-post-down.d/wpasupplicant

	# Put the wpa_ctrl.h, config_ssid.h, driver.h, defs.h, includes.h, common.h, os.h and build_config.h
	# files to ${includedir}. The Wilink demo applications build requires wpa-supplicant header files support.
	install -d ${D}/${includedir}
	install -m 644 ${S}/wpa_ctrl.h ${D}/${includedir}
	install -m 644 ${S}/config_ssid.h ${D}/${includedir}
	install -m 644 ${S}/driver.h ${D}/${includedir}
	install -m 644 ${S}/defs.h ${D}/${includedir}
	install -m 644 ${S}/includes.h ${D}/${includedir}
	install -m 644 ${S}/common.h ${D}/${includedir}
	install -m 644 ${S}/os.h ${D}/${includedir}
	install -m 644 ${S}/build_config.h ${D}/${includedir}
}

SRC_URI[md5sum] = "bd2436392ad3c6d2513da701a12f2d27"
SRC_URI[sha256sum] = "cf688be96ba5f3227876b3412150e84a3cee60ddd0207b6d940d1fbbaf136b57"
