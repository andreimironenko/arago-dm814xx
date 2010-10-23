require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti816x"

BRANCH = "ti816x-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI816XPSP_04.00.00.07"

UVER = "2010.06"
PSPREL = "04.00.00.07"

PR = "r1"

UBOOT_MACHINE = "ti8168_evm_min_sd"
UBOOT_BINARY ?= "u-boot.bin"
UBOOT_MLO_SYMLINK ?= "MLO"
UBOOT_MLO_IMAGE ?= "MLO-${MACHINE}-${PV}-${PR}"

do_install() {
    cp ${S}/${UBOOT_BINARY} ${S}/${UBOOT_MLO_IMAGE}
    install -d ${D}/boot
    install ${S}/${UBOOT_MLO_IMAGE} ${D}/boot/${UBOOT_MLO_IMAGE}
    ln -sf ${UBOOT_MLO_IMAGE} ${D}/boot/${UBOOT_MLO_SYMLINK}
} 

do_deploy() {
    cp ${S}/${UBOOT_BINARY} ${S}/${UBOOT_MLO_IMAGE}
    install -d ${DEPLOY_DIR_IMAGE}
    install ${S}/${UBOOT_MLO_IMAGE} ${DEPLOY_DIR_IMAGE}/${UBOOT_MLO_IMAGE}
    package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${UBOOT_MLO_IMAGE}

    cd ${DEPLOY_DIR_IMAGE}
    rm -f ${UBOOT_MLO_SYMLINK}
    ln -sf ${UBOOT_MLO_IMAGE} ${UBOOT_MLO_SYMLINK}
    package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${UBOOT_MLO_SYMLINK}
}
