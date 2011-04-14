require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti816x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI816XPSP_04.00.00.10"

UVER = "2010.06"
PSPREL = "04.00.00.10"

SRC_URI =+ "file://0001-TI816x-Add-default-support-for-800Mhz.patch;apply=yes \
"

PR = "r1"

UBOOT_MACHINE = "ti8168_evm_config"
UBOOT_MAKE_TARGET = "u-boot.ti"
TI_UBOOT_BINARY = "u-boot.noxip.bin"

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
