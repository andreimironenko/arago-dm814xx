require u-boot_${PV}.inc

PR = "${INC_PR}.0"

UBOOT_MACHINE = "ti8168_evm_min_sd"
UBOOT_BINARY = "u-boot.min.sd"
UBOOT_SYMLINK = "MLO"
UBOOT_IMAGE = "MLO-${MACHINE}-${PV}-${PR}"

# Look to the base recipes directory to find files as well.
FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-${PV}"
