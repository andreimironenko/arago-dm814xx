TARGET_ARCH = "arm"
SOC_FAMILY = "tnetv107x"

require conf/machine/include/tune-arm1176-novfp.inc

PREFERRED_PROVIDER_virtual/kernel = "linux-tnetv107x"
MACHINE_KERNEL_PR = "r1"
KERNEL_IMAGETYPE = "uImage"

PREFERRED_PROVIDER_virtual/bootloader = "u-boot"
PREFERRED_VERSION_u-boot = "tnetv107x"
UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
EXTRA_IMAGEDEPENDS += "u-boot"
