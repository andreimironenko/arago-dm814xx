require u-boot_${PV}.inc

PR = "${INC_PR}.1"

COMPATIBLE_MACHINE = "ti811x"

# Set PROVIDES so that we don't define virtual/bootloader for this recipe.
# This causes a non-critical parsing error about multiple providers which
# makes the build return a non-zero status.  This leads to the build
# being marked as a failure even though it shows a successful build.
PROVIDES = "u-boot-min-sd"

UBOOT_MACHINE_ti811x = "ti811x_evm_min_sd"
UBOOT_MACHINE_ti814x = "ti8148_evm_min_sd"
UBOOT_MACHINE_ti816x = "ti8168_evm_min_sd"
UBOOT_BINARY = "u-boot.min.sd"
# UBOOT_SYMLINK = "MLO"
UBOOT_IMAGE = "MLO"

# Look to the base recipes directory to find files as well.
FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-${PV}"
