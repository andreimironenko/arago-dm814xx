DESCRIPTION = "ti compat-bluetooth drivers"
HOMEPAGE = "https://gforge.ti.com/gf/project/ecs_nlcp/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"

RDEPENDS += "ti-compat-wireless-wl12xx"

TAG = "${@'${COMPAT_BLUETOOTH_VERSION}'.replace('-', '')}"
PV = "0.${TAG}"

S = "${WORKDIR}/compat-bluetooth-2-6/"

inherit module

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D}"

do_configure() {
	cd ${S}
        ./scripts/driver-select bt
}

do_install() {
	oe_runmake DEPMOD=echo DESTDIR="${D}" INSTALL_MOD_PATH="${D}" LDFLAGS="" install-modules
	find ${D} -name compat.ko | xargs rm
}
