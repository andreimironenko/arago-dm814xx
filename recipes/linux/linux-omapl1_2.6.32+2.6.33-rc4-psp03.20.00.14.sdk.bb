require linux-omapl1.inc

KVER = "2.6.33-rc4"
PSPREL = "03.20.00.14.sdk"

SRCREV = "v2.6.33-rc4_DAVINCIPSP_03.20.00.14"

SRC_URI_append_omapl138 = " \
	file://0001-uio_pruss1-Core-driver-addition.patch \
	file://0002-uio_pruss2-Platform-changes.patch \
	file://0003-uio_pruss3-Workarounds-put-into-core-code.patch \
	"

# WiLink patches
WILINK_PATCHES = " \
	file://0001-Supported-MMC2-interface-on-AM1808-board.patch \
	file://0002-Supporting-Standard-SDIO-For-WL1271-DC-on-AM1808-EVM.patch \
	file://0003-Added-wilink-driver-for-WL1271-on-AM1808-EVM.patch \
	file://0004-Supported-AFE-for-UART1-on-AM1808-platform.patch \
	file://0005-Modified-defconfig-for-WL1271-DC-support-on-AM1808.patch\
	"

SRC_URI_append_am180x-evm = "${WILINK_PATCHES}"

sysroot_stage_all_append() {
	if [ -d drivers/net/wireless/wilink ]
	then
		mkdir -p $kerneldir/drivers/net/wireless/wilink
		cp -fR drivers/net/wireless/wilink/* $kerneldir/drivers/net/wireless/wilink
	fi
}
