require linux-omapl1.inc

KVER = "2.6.33-rc4"
PSPREL = "03.20.00.14.sdk"

SRCREV = "v2.6.33-rc4_DAVINCIPSP_03.20.00.14"

COMPATIBLE_MACHINE = "am180x-evm|da850-omapl138-evm"

# Add PSP supported patches that go on top of the 03.20.00.14 release
# These patches should be removed with the next PSP release.
SRC_URI += " \
    file://0001-DA850-OMAP-L138-Enable-UART1-RTS-CTS-line-pinmuxing.patch \
    file://0002-DA8xx-Force-DaVinci-UART-s-to-be-of-AR7-port-type.patch \
    file://0003-Serial_8250-Modify-check-to-enable-AFE-for-AR7-port-.patch \
    file://0004-DA850-OMAP-L138-Disable-modem-status-interrupts.patch \
    file://0005-Serial_8250-Modify-the-AR7-port-RX-fifo-threshold.patch \
    file://0006-da850-omap-l138-Correct-a-bug-while-checking-for-MMC.patch \
    file://0007-da850-omap-l138-Configure-GP0-11-only-when-UI-card-i.patch \
    "

SRC_URI += " \
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

# Apply the patch to disable BT on OMAPL138 only
SRC_URI_append_da850-omapl138-evm = " \
   file://0001-L138-Support-MCASP-and-disable-BT-for-L138-Platform.patch \
  "

SRC_URI += "${WILINK_PATCHES}"

sysroot_stage_all_append() {
	if [ -d drivers/net/wireless/wilink ]
	then
		mkdir -p $kerneldir/drivers/net/wireless/wilink
		cp -fR drivers/net/wireless/wilink/* $kerneldir/drivers/net/wireless/wilink
	fi
}
