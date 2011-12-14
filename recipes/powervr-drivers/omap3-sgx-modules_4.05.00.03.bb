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

SGXPV = "4_05_00_03"
IMGPV = "1.6.16.4117"
BINFILE = "Graphics_SDK_setuplinux_${SGXPV}.bin"

inherit module

MACHINE_KERNEL_PR_append = "e"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/4_05_00_03/exports/Graphics_SDK_setuplinux_4_05_00_03.bin \
          "

SRC_URI[md5sum] = "0e651eaa92bb91760f0b40a17697a7dc"
SRC_URI[sha256sum] = "bfe764a8959556195545d6fff76f63a489642f345c105bbbc309a3f243c2dd0e"

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
