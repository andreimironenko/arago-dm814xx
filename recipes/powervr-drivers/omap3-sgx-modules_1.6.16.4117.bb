# Internal amsdk recipe for now until the toolchain is sync'ed and there
# are public releases.

DESCRIPTION = "Kernel drivers for the PowerVR SGX chipset found in the omap3 SoCs"
LICENSE = "GPLv2"

# download required binary distribution from:
# http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/latest/index_FDS.html
# see libgles-omap3.inc for detailed installation instructions

TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"
require ../ti/ti-eula-unpack.inc
# For accessing SUPPORT_XORG value
require powervr.inc

SGXPV = "4_05_00_02"
IMGPV = "1.6.16.4117"
BINFILE = "Graphics_SDK_setuplinux_${SGXPV}.bin"

inherit module

MACHINE_KERNEL_PR_append = "c"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/${SGXPV}/exports/Graphics_SDK_setuplinux_${SGXPV}.bin"

SRC_URI[md5sum] = "710f397849c3d1fbc7ec84a0cba980f5"
SRC_URI[sha256sum] = "0625308c31e32eadd5fa48e760d1b18305aa3d8b89375be8cfb29dd4f222c1d6"

S = "${WORKDIR}/Graphics_SDK_${SGXPV}/GFX_Linux_KM"

PVRBUILD = "release"

PACKAGE_STRIP = "no"

export SUPPORT_XORG

TI_PLATFORM_omap3 = "omap3630"
TI_PLATFORM_ti816x = "ti81xx"
TI_PLATFORM_ti33x = "ti335x"

MODULESLOCATION_omap3 = "dc_omapfb3_linux"
MODULESLOCATION_ti816x = "dc_ti81xx_linux"
MODULESLOCATION_ti33x = "dc_ti335x_linux"

MAKE_TARGETS = " BUILD=${PVRBUILD} TI_PLATFORM=${TI_PLATFORM} SUPPORT_XORG=${SUPPORT_XORG}"

do_install() {
	mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/gpu/pvr
	cp ${S}/pvrsrvkm.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/gpu/pvr
	if [ "$SUPPORT_XORG" = "1" ]; then
	cp ${S}/services4/3rdparty/linux_drm/drm.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/gpu/pvr
	else
	cp ${S}/services4/3rdparty/${MODULESLOCATION}/omaplfb.ko  ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/gpu/pvr
	fi
	cp ${S}/services4/3rdparty/bufferclass_ti/bufferclass_ti.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/gpu/pvr
}
