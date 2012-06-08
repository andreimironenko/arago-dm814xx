DESCRIPTION = "Kernel drivers for the PowerVR SGX chipset found in the omap3 SoCs"
LICENSE = "GPLv2"

# download required binary distribution from:
# http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/latest/index_FDS.html
# see libgles-omap3.inc for detailed installation instructions

TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"
require ../ti/ti-eula-unpack.inc

SGXPV = "4_04_00_02"
IMGPV = "1.6.16.4117"
BINFILE := "Graphics_SDK_setuplinux_${SGXPV}.bin"

inherit module

MACHINE_KERNEL_PR_append = "i"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/${SGXPV}/exports/Graphics_SDK_setuplinux_${SGXPV}.bin \
           file://0001-GFX_Linux_KM-Fix-os-functions.patch;striplevel=2 \
"
SRC_URI[md5sum] = "901327765c0991900afda91473f84725"
SRC_URI[sha256sum] = "2ced2329ec819e42d57eca1a11120b02ec9c332fb63baa0a41285eed07d4b48e"

S = "${WORKDIR}/Graphics_SDK_${SGXPV}/GFX_Linux_KM"

PVRBUILD = "release"

PACKAGE_STRIP = "no"

export SUPPORT_XORG

TI_PLATFORM_omap3 = "omap3630"
TI_PLATFORM_ti816x = "ti81xx"
TI_PLATFORM_ti814x = "ti81xx"
TI_PLATFORM_ti811x = "ti81xx"
TI_PLATFORM_ti33x = "ti335x"

MODULESLOCATION_omap3 = "dc_omapfb3_linux"
MODULESLOCATION_ti816x = "dc_ti81xx_linux"
MODULESLOCATION_ti814x = "dc_ti81xx_linux"
MODULESLOCATION_ti811x = "dc_ti81xx_linux"
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
