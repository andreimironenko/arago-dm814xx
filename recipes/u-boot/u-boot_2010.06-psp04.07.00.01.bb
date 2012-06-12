require u-boot_${PV}.inc

PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "ti811x"

UBOOT_MACHINE_ti811x = "ti811x_evm_config"
UBOOT_MACHINE_ti814x = "ti8148_evm_config"
UBOOT_MACHINE_ti816x = "ti8168_evm_config"

# This is the NAND binary which is signed to be booted by the boot ROM
TI_UBOOT_BINARY_ti811x = "u-boot.bin"
TI_UBOOT_BINARY_ti814x = "u-boot.bin"
TI_UBOOT_BINARY_ti816x = "u-boot.noxip.bin"

# Append the install and deploy functions so that we ship the
# u-boot.bin file, which is used in the case of SD card boot,
# and u-boot.noxip.bin, which is used in the case of NAND boot.
# We want both files installed in the image as well as put in
# the deploy directory.
# NOTE: Should we try to cleanup the links/rename them to make this
#       more clear?
do_install_append () {
    install ${S}/${TI_UBOOT_BINARY} ${D}/boot/${TI_UBOOT_BINARY}
}

do_deploy_append () {
    install ${S}/${TI_UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/${TI_UBOOT_BINARY}
    package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${TI_UBOOT_BINARY}

    cd ${DEPLOY_DIR_IMAGE}
    rm -f ${UBOOT_SYMLINK}
    ln -sf ${TI_UBOOT_BINARY} ${UBOOT_SYMLINK}
}
