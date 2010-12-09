DESCRIPTION = "Bluetooth enable for WL1271 chipset"
SECTION = "network"
LICENSE = "GPLv2 BSD"
DEPENDS += "virtual/kernel"

MACHINE_KERNEL_PR_append = "b"

COMPATIBLE_MACHINE = "am180x-evm"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/ecs/WL1271_Linux_SDK/AM18x/exports/3.20.00.13_M6.01/wl1271-demos.tar.gz"

S = "${WORKDIR}/wl1271-demos/GPIO"

inherit module

export BASE_BUILDOS="${STAGING_KERNEL_DIR}"

do_install () {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/bt_enable
	install -m 0755 ${S}/gpio_en.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/bt_enable
}

PACKAGE_STRIP = "no"

SRC_URI[md5sum] = "f9aae8a78fa6d9c1a31a4cdc3d3af7fe"
SRC_URI[sha256sum] = "7081da34338cb7d0318ef98bfb62cd1ebc17c3f919c5a830b22b4ae2ee27100c"
